package model;

import java.util.ArrayList;

//TODO In future develop login for users

public class User {
    private String name;
    private String login;
    private String password;
    private String type;
    ArrayList<Event> eventList = new ArrayList<>();

    public User(String name, String login, String password, String type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }
}
