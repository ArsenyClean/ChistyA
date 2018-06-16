package music_application.control;

import music_application.model.Address;
import music_application.model.MusicType;
import music_application.model.Role;
import music_application.model.User;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class MusicStore {

    private static   Logger LOG = LoggerFactory.getLogger(MusicStore.class);

    private Properties properties = new Properties();

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static MusicStore instance = new MusicStore();

    public static MusicStore getInstance() {
        return instance;
    }

    private MusicStore() {
        PoolProperties p = new PoolProperties();
        p.setUrl(this.properties.getProperty("url"));
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername(this.properties.getProperty("user"));
        p.setPassword(this.properties.getProperty("password"));
        Connection connection = connectionPool.getConnection();
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        PreparedStatement statement = null;
        try {
            connection = datasource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        StringBuilder builder = new StringBuilder();
        try {
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                    "music (id SERIAL PRIMARY KEY, name VARCHAR(30));" +
                    "CREATE TABLE IF NOT EXISTS role " +
                    "(id SERIAL PRIMARY KEY, name VARCHAR(30));" +
                    "(id SERIAL PRIMARY KEY, country VARCHAR(30), city VARCHAR(30), " +
                    "street VARCHAR(30), house VARCHAR(30), flat VARCHAR(30));" +
                    "CREATE TABLE IF NOT EXISTS users " +
                    "(id SERIAL PRIMARY KEY, name VARCHAR(30), " +
                    "login VARCHAR(40), password VARCHAR(10), address_id INTEGER REFERENCES address(id), " +
                    "role_id INTEGER REFERENCES role(id), create_date TIMESTAMP);" +
                    "CREATE TABLE IF NOT EXISTS user_music " +
                    "(user_id INTEGER REFERENCES users(id), music_type_id INTEGER REFERENCES music_type(id));");
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            for(Enum value : Arrays.asList(Role.values())) {
                statement = connection.prepareStatement("INSERT INTO role (name) " +
                        "SELECT ? WHERE NOT EXISTS (SELECT id FROM role WHERE name = ?);");
                statement.setString(1, value.name());
                statement.setString(2, value.name());
                statement.executeUpdate();
            }
            for(Enum value : Arrays.asList(MusicType.values())) {
                statement = connection.prepareStatement("INSERT INTO music (name) " +
                        "SELECT ? WHERE NOT EXISTS (SELECT id FROM music WHERE name = ?);");
                statement.setString(1, value.name());
                statement.setString(2, value.name());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String createUser(  String name,   String login,   String password, 
                               Address adress,   Role role,   List<MusicType> music) {
        String userId;
        this.createAdress(adress);
        userId = this.createUser(name, login, password,
                this.getAddressId(adress), this.getRoleId(role));
        this.insertUserMusic(userId, music);
        return userId;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new LinkedList<>();
        String sql = "SELECT * FROM users;";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rSet = ps.executeQuery()) {
                while(rSet.next()) {
                    allUsers.add(this.getuserbyid(rSet.getString("user_id")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return allUsers;
    }

    public User getuserbyid(String userId) {
        String userName = null;
        String userLogin = null;
        String userPassword = null;
        Timestamp create_date = null;
        int currentUserAddressId = 0;
        int currentUserRoleId = 0;
        User foundUser = null;
        Address currentAddress = null;
        List<MusicType> currentUserMusicTypes = new LinkedList<>();
        Role currentUserRole = null;
        PreparedStatement statement = null;
        try {
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?;");
            statement.setString(1, userId);
            ResultSet resultSet1 = null;
            resultSet1 = statement.executeQuery();
            while (resultSet1.next()) {
                userName = resultSet1.getString("name");
                userLogin = resultSet1.getString("login");
                userPassword = resultSet1.getString("password");
                create_date = resultSet1.getTimestamp("create_date");
                currentUserAddressId = resultSet1.getInt("address_id");
                currentUserRoleId = resultSet1.getInt("role_id");
                currentAddress = this.getAddressById(currentUserAddressId);
                currentUserRole = this.getRoleById(currentUserRoleId);

                statement = connection.prepareStatement("SELECT music_type.name FROM user_music " +
                                                    "INNER JOIN users " +
                                                    "ON users.id = user_music.user_id " +
                                                    "INNER JOIN music " +
                                                    "ON music.id = user_music.music_type_id " +
                                                    "WHERE users.user_id = ?;");
                statement.setString(1, userId);
                resultSet1 = statement.executeQuery();
                while (resultSet1.next()) {
                    currentUserMusicTypes.add(MusicType.valueOf(resultSet1.getString("name")));
                }
                foundUser = new User(userId, userName, userLogin, userPassword,
                        currentAddress, currentUserRole, currentUserMusicTypes, create_date);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundUser;
    }

    public void deleteUser(  String userId) {
        try {
            PreparedStatement statement = null;
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("DELETE FROM user_music WHERE user_id = " +
                    "(SELECT id FROM users WHERE user_id = ?);");
            statement.setString(1, userId);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM users WHERE user_id = ?;");
            statement.setString(1, userId);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM address WHERE user_id = " +
                    "(SELECT id FROM users WHERE user_id = ?);");
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User getUserIdByLoginAndPassword(  String userLogin,   String userPassword) {
        User foundUser = null;
            try {
                PreparedStatement statement = null;
                Connection connection = connectionPool.getConnection();
                statement = connection.prepareStatement("SELECT user_id FROM users WHERE login = ? AND password = ?;");
                statement.setString(1, userLogin);
                statement.setString(2, userPassword);
                ResultSet resultSet = null;
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    foundUser = this.getuserbyid(resultSet.getString("user_id"));
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        return foundUser;
    }

    public void updateUser(  String oldUserId,   String userName,
                             String userlogin,   String userPassword,   Address userAddress,
                             Role userRole,   List<MusicType> userMusicTypeList) {
        this.deleteUser(oldUserId);
        this.createUser(userName, userlogin, userPassword, userAddress,
                        userRole, userMusicTypeList);
    }

    private void createAdress(  Address address) {
            try {
                PreparedStatement statement = null;
                Connection connection = connectionPool.getConnection();
                statement = connection.prepareStatement("INSERT INTO address(country, city, street, house, flat)" +
                        "VALUES (?, ?, ?, ?, ?);");
                statement.setString(1, address.getCountry());
                statement.setString(2, address.getCity());
                statement.setString(3, address.getStreet());
                statement.setString(4, address.getHouse());
                statement.setString(5, address.getFlat());
                statement.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
    }

    private int getAddressId(  Address address) {
        int addressId = 0;
        try {
            PreparedStatement statement = null;
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT id FROM address WHERE country = ? " +
                    "AND city = ? AND street = ? AND house = ? AND flat = ?;");
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getHouse());
            statement.setString(5, address.getFlat());
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                addressId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return addressId;
    }

    private Address getAddressById(  int addressId) {
        Address foundAddress = null;
        try {
            PreparedStatement statement = null;
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE id = ?;");
            statement.setInt(1, addressId);
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foundAddress = new Address(resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("house"),
                        resultSet.getString("flat"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundAddress;
    }

    private int getRoleId(  Role role) {
        int roleId = 0;
        try {
            PreparedStatement statement = null;
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT id FROM role WHERE name = ?;");
            statement.setString(1, role.name());
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return roleId;
    }

    private Role getRoleById(  int roleId) {
        Role foundRole = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
            statement = connection.prepareStatement("SELECT name FROM role WHERE id = ?;");
            statement.setInt(1, roleId);
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foundRole = Role.valueOf(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundRole;
    }

    private String createUser(  String name,   String login,
                                String password,   int addressId,   int roleId) {
        String userId = null;
        try {
            PreparedStatement statement = null;
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("INSERT INTO users(user_id, name, " +
                    " login, password, address_id, role_id, create_date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, String.valueOf(System.currentTimeMillis()));
            statement.setString(2, name);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setInt(5, addressId);
            statement.setInt(6, roleId);
            statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            statement = connection.prepareStatement("SELECT user_id FROM users WHERE address_id = ?;");
            statement.setInt(1, addressId);
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getString("user_id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return userId;
    }

    private int getMusicTypeId(  MusicType musicType) throws SQLException {
        int result = 0;
        PreparedStatement statement = null;
        Connection connection = connectionPool.getConnection();
        statement = connection.prepareStatement("SELECT id FROM music_type WHERE name = ?;");
        statement.setString(1, musicType.name());
        ResultSet resultSet = null;
        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getInt("id");
        }
        return result;
    }

    private void insertUserMusic (  String userId,   List<MusicType> userMusicTypesList) {
        int userSqlId = 0;
        int userMusicTypeId = 0;
        try {
            PreparedStatement statement = null;
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT id FROM users WHERE user_id = ?;");
            statement.setString(1, userId);
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userSqlId = resultSet.getInt("id");
            }
            for (MusicType musicType: userMusicTypesList) {
                userMusicTypeId = this.getMusicTypeId(musicType);
                statement = connection.prepareStatement("INSERT INTO user_music(user_id, music_type_id)" +
                        "VALUES (?, ?);");
                statement.setInt(1, userSqlId);
                statement.setInt(2, userMusicTypeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}