package dao;

import model.User;

import java.util.List;

//TODO In future develop login for users

public interface UserDao {
    void add(User user);
    User find(int id);
    void remove(int id);

    List<User> getAll();

}
