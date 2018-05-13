package music_application.model;

import java.sql.Timestamp;
import java.util.List;

public class User {

    private   String id;

    private   String name;

    private   String login;

    private   String password;

    private   Address address;

    private   Role role;

    private   List<MusicType> musicTypes;

    private   Timestamp createDate;

    public User(  String id,   String name,   String login,   String password,
                  Address address,   Role role,   List<MusicType> musicTypes,   Timestamp createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.address = address;
        this.role = role;
        this.musicTypes = musicTypes;
        this.createDate = createDate;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public Address getAddress() {
        return this.address;
    }

    public Role getRole() {
        return this.role;
    }

    public List<MusicType> getMusicTypes() {
        return this.musicTypes;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

}