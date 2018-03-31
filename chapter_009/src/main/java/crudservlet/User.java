package crudservlet;

import javax.servlet.http.HttpServlet;
import java.sql.Timestamp;
import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String email;
    private String createDate;

    public User(Integer id, String name, String login, String email, String createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String email, String createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
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

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "User name= " + this.name + " User login= " + this.login +
                " User email= " + this.email + " User created date= " + this.createDate;
    }
}