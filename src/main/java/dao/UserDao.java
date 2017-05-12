package dao;

import model.User;

import java.util.List;

/**
 * Created by ppolak on 12.05.17.
 */
public interface UserDao {
    void add(User user);
    User find(int id);
    void remove(int id);

    List<User> getAll();

}
