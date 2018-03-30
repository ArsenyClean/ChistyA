package crudservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserStore {

    DataSource instance;

    public UserStore(DataSource source) {
        this.instance = source;
        this.start();
    }

    private Logger LOG = LoggerFactory.getLogger(UserStore.class);
    private Connection connection;
    private PreparedStatement pr;
    private ResultSet rs;

    private final String url = "jdbc:postgresql://localhost:5432/userstore";
    private final String username = "postgres";
    private final String pasword = "1234";

    private void start() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, pasword);
            connection.setAutoCommit(false);
            if (connection != null) {
                connection.createStatement().execute("CREATE TABLE IF NOT EXISTS users " +
                        "(id SERIAL PRIMARY KEY, name varchar(30), login varchar(30), email varchar(30), date_creation varchar(30));");
            } else {
                LOG.info("Connection didnt hapened ");
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User getById(Integer id) {
        User user = null;
        try {
            pr = connection.prepareStatement("SELECT id, name, login, email, date_creation from users where id=(?);");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            connection.commit();
            if(rs.isBeforeFirst()) {
                rs.next();
                user = new User(rs.getInt(1), rs.getString(2),rs.getString(3),
                        rs.getString(4), rs.getString(5));
            } else {
                LOG.info("There is no such user ");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public int add(User user) {
        int id = -1;
        try {
            pr = connection.prepareStatement("insert into users(name, login, email, date_creation) values (?,?,?,?) RETURNING id;");
            System.out.println(user.toString());
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getEmail());
            pr.setString(4, user.getCreateDate());
            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean update(Integer id, String name, String login, String email) {
        boolean result = false;
        try {
            pr = connection.prepareStatement("UPDATE users set name=(?), login=?, email=(?) where id=?;");
            pr.setString(1, name);
            pr.setString(2, login);
            pr.setString(3, email);
            pr.setInt(4, id);
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean delete(Integer id) {
        boolean result = false;
        try {
            pr = connection.prepareStatement("DELETE from users WHERE id=(?);");
            pr.setInt(1, id);
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return  result;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            pr = connection.prepareStatement("SELECT * from users;");
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getString(5)));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }
}