package crudservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserStore {

    private final static UserStore instance = new UserStore();

    private Logger LOG = LoggerFactory.getLogger(UserStore.class);
    private Connection connection;
    private PreparedStatement pr;
    private ResultSet rs;

    public UserStore() {
        this.start();
    }

    public static UserStore getInstance() {
        return instance;
    }

    private void start() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userstore", "postgres", "1234");
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

    public User get(String login) {
        User user = null;
        try {
            pr = connection.prepareStatement("SELECT name, login, email, date_creation from users where login=(?);");
            pr.setString(1, login);
            rs = pr.executeQuery();
            connection.commit();
            if(rs.isBeforeFirst()) {
                rs.next();
                user = new User(rs.getString(1),rs.getString(2),
                        rs.getString(3), rs.getString(4));
            } else {
                LOG.info("There is no such user ");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    public boolean add(User user) {
        boolean result = false;
        try {
            pr = connection.prepareStatement("insert into users(name, login, email, date_creation) values (?,?,?,?);");
            System.out.println(user.toString());
            pr.setString(1, user.getName());
            pr.setString(2, user.getLogin());
            pr.setString(3, user.getEmail());
            pr.setString(4, user.getCreateDate());
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(User user) {
        boolean result = false;
        try {
            pr = connection.prepareStatement("UPDATE users set name=(?), email=(?) where login=?;");
            pr.setString(1, user.getName());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getLogin());
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean delete(String login) {
        boolean result = false;
        try {
            pr = connection.prepareStatement("DELETE from users WHERE login=(?);");
            pr.setString(1, login);
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return  result;
    }
}