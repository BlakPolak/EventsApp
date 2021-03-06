package controller;

import dao.EventDao;
import dao.EventDaoSqlite;
import model.Category;
import model.Event;
import spark.ModelAndView;

import java.util.*;


public class EventController {

    public ModelAndView showEvents() {
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        ArrayList<Event> events = (ArrayList<Event>) eventDao.getAll();
        viewObjects.put("events", events);
        viewObjects.put("categories", eventDao.getCategories());
        ModelAndView model = new ModelAndView(viewObjects, "index");
        return model;
    }

    public ModelAndView getEvent(Integer id) {
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        Event event = eventDao.find(id);
        viewObjects.put("event", event);
        ModelAndView model = new ModelAndView(viewObjects, "details");
        return model;
    }

    public ModelAndView eventForm() {
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        viewObjects.put("categories", eventDao.getCategories());
        ModelAndView model = new ModelAndView(viewObjects, "add_event");
        return model;
    }

    public void createEvent(String name, String categoryName, String description, String startDate) {
        EventDao eventDao = new EventDaoSqlite();
        Category newCategory = new Category(categoryName);
        Event newEvent = new Event(name, newCategory, description, startDate);
        eventDao.add(newEvent);
    }

    public ModelAndView getEventToUpdate(Integer id) {
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        Event event = eventDao.find(id);
        viewObjects.put("event", event);
        viewObjects.put("categories", eventDao.getCategories());
        ModelAndView model = new ModelAndView(viewObjects, "update_event");
        return model;
    }

    public void updateEvent(Integer id, String name, String categoryName, String description, String startDate) {
        EventDao eventDao = new EventDaoSqlite();
        eventDao.update(id, name, categoryName, description, startDate);
    }

    public void removeEvent(int id) {
        EventDao eventDao = new EventDaoSqlite();
        eventDao.remove(id);
    }

    public ModelAndView getByCategory(String category) {
        Map<String, Object> viewObjects = new HashMap<>();
        EventDao eventDao = new EventDaoSqlite();
        List<Event> filterByCategory = new ArrayList<>();
        ArrayList<Event> events = (ArrayList<Event>) eventDao.getAll();
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event itrNext = iterator.next();
            if ((Objects.equals(itrNext.getCategoryName(), category))) {
                filterByCategory.add(itrNext);
            }
        }
        viewObjects.put("events", filterByCategory);
        viewObjects.put("categories", eventDao.getCategories());
        ModelAndView model = new ModelAndView(viewObjects, "index");
        return model;
    }
}
