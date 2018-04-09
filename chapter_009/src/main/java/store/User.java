package store;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private Role role;
    private String email;
    private String createDate;

    public User(Integer id, String name, String login, String email, String password, String role, String createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = new Role(role);
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String email, String password, String role, String createDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = new Role(role);
        this.email = email;
        this.createDate = createDate;
    }


    public User(String name, String login,  String email, String password, String rolename) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = new Role(rolename);
        this.email = email;
        this.createDate = new Timestamp(System.currentTimeMillis()).toString();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.getRoleName();
    }

    public void setRole(String role) {
        this.role.setRoleName(role);
    }

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "User name= " + this.name + " User login= " + this.login +
                " User email= " + this.email + " User created date= " + this.createDate;
    }
}