package dao;


import model.Event;

import java.util.List;

public interface EventDao {
    void add(Event event);
    Event find(int id);
    void remove(int id);

    List<Event> getAll();
    List<Event> getBy(String category);
}
