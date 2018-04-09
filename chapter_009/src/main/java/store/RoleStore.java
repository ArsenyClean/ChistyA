package store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleStore {

    DataSource roleInstance;

    public RoleStore(DataSource source) {
        this.roleInstance = source;
        this.start();
    }

    private Logger LOG = LoggerFactory.getLogger(UserStore.class);
    private Connection connection;

    private final String url = "jdbc:postgresql://localhost:5432/userstore";
    private final String username = "postgres";
    private final String pasword = "1234";

    private void start() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, pasword);
            connection.setAutoCommit(false);
            if (connection != null) {
                connection.createStatement().execute("CREATE TABLE IF NOT EXISTS roles " +
                        "(id SERIAL PRIMARY KEY, name varchar(30);");
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


    public Role getRoleById(Integer id) {
        Role role = null;
        try {
            PreparedStatement pr;
            ResultSet rs;
            pr = connection.prepareStatement("SELECT id, name from roles where id=(?);");
            pr.setInt(1, id);
            rs = pr.executeQuery();
            connection.commit();
            if(rs.isBeforeFirst()) {
                rs.next();
                role = new Role(rs.getString(2), rs.getInt(1));
            } else {
                LOG.info("There is no such user ");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return role;
    }

    public void addRole(Role role) {
        try {
            PreparedStatement pr;
            pr = connection.prepareStatement("insert into roles(name) values (?) RETURNING id;");
            pr.setString(1, role.getRoleName());
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    int i = resultSet.getInt("id");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRole(Integer id, String name) {
        boolean result = false;
        try {
            PreparedStatement pr;
            pr = connection.prepareStatement("UPDATE roles set name=(?) where id=(?);");
            pr.setString(1, name);
            pr.setInt(2, id);
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean deleteRole(Integer id) {
        boolean result = false;
        try {
            PreparedStatement pr;
            pr = connection.prepareStatement("DELETE from roles WHERE id=(?);");
            pr.setInt(1, id);
            pr.execute();
            connection.commit();
            result = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return  result;
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            PreparedStatement pr;
            pr = connection.prepareStatement("SELECT * from roles;");
            try (ResultSet resultSet = pr.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getString(2), resultSet.getInt(1)));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return roles;
    }

    @Override
    public String toString() {
        List<Role> roles = this.getRoles();
        StringBuilder stringBuilder = new StringBuilder();
        for (Role role: roles) {
            stringBuilder.append("id=" + role.getId() + " name: " + role.getRoleName() + "\n");
        }
        return stringBuilder.toString();
    }
}