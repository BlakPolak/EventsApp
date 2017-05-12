package dao;


import model.Category;
import model.Event;

import java.util.List;

public interface EventDao {
    void add(Event event);
    Event find(Integer id);
    void remove(Integer id);

    List<Event> getAll();
    List<Event> getBy(Category category);
}
