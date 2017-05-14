package controller;

import spark.Request;
import spark.Response;


import dao.EventDao;
import dao.EventDaoSqlite;
import model.Event;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EventController {

    public ModelAndView showEvents() {
        //Get events from database by Dao
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        ArrayList<Event> events = (ArrayList<Event>) eventDao.getAll();
        viewObjects.put("events", events);

        ModelAndView model = new ModelAndView(viewObjects, "index");

        return model;
    }

    public ModelAndView getEvent(Integer id) {
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        Event event = eventDao.find(id);
        viewObjects.put("event", event);
        viewObjects.put("id", id);

        ModelAndView model = new ModelAndView(viewObjects, "details");

        return model;
    }

    public ModelAndView createEvent(Request req, Response res) {
        ModelAndView model = new ModelAndView("", "create_event");

        return model;
    }
}
