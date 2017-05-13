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

    public ModelAndView showEvents(Request req, Response res) {
        //Get events from database by Dao
        EventDao eventDao = new EventDaoSqlite();
        Map<String, Object> viewObjects = new HashMap<>();
        ArrayList<Event> events = (ArrayList<Event>) eventDao.getAll();
        viewObjects.put("events", events);

        ModelAndView model = new ModelAndView(viewObjects, "index");

        return model;
    }

    public ModelAndView createEvent(Request req, Response res) {
        ModelAndView model = new ModelAndView("", "create_event");

        return model;
    }
}
