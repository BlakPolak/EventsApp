package dao;

import model.Category;
import model.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventDaoSqlite implements EventDao{

    @Override
    public void add(Event event) {
        
    }

    @Override
    public Event find(Integer id) {
        Event event = null;
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events where id = " + Integer.toString(id));

            if(rs.next()) {
                String myDateStr = rs.getString("date");
                SimpleDateFormat sdf = new SimpleDateFormat("EEE dd MMM HH:mm y");
                Date myDate = sdf.parse(myDateStr);
                event = new Event(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getString("description"),
                        myDate
                );
                event.setId(rs.getInt("id"));
            }

        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return event;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM events");
            while(rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getString("description"),
                        format.parse(rs.getString("date"))
                );
                events.add(event);
            }
        } catch(SQLException e) {
            System.out.println("Second connection to DB failed");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return events;
    }

    @Override
    public List<Event> getBy(Category category) {
        List<Event> events = new ArrayList<>();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events where category = " + Integer.toString(category.getCategoryId()));
            while(rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getString("description"),
                        new Date(rs.getString("date"))
                );
                events.add(event);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return events;
    }
}



