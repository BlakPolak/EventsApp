package dao;


import model.Category;
import model.Event;

import java.util.List;

public interface EventDao {
    void add(Event event);
    Event find(Integer id);
    void remove(Integer id);

    List<Event> getAll();

    List<Category> getCategories();

    List<Event> getBy(Category category);

    void update(Integer id, String name, String categoryName, String description, String startDate);
}
