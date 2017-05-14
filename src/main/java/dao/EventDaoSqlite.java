package dao;

import model.Category;
import model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EventDaoSqlite implements EventDao{

    @Override
    public void add(Event event) {
        try {
            Connection connection = SqliteJDBCConnector.connection();
            String insertQuery = "INSERT INTO events(id, name, category, description, date) VALUES(?, ?, ?, ?, ?);";

            // Prepared statements allow us to avoid SQL injection attacks
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);

            // JDBC binds every prepared statement argument to a Java Class such as Integer and or String
            pstmt.setInt(1, event.getId());
            pstmt.setString(2, event.getName());
            pstmt.setString(3, event.getCategoryName());
            pstmt.setString(4, event.getDescription());
            pstmt.setString(5, event.getStartDate());


            pstmt.executeUpdate();

            // Unless closed prepared statement connections will linger
            // Not very important for a trivial app but it will burn you in a professional large codebase

//            Statement statement = connection.createStatement();
//            statement.executeUpdate(String.format("INSERT INTO events(id, name, category, description, date) VALUES (%d, '%s, '%s', '%s', '%s')",
//                    event.getId(), event.getName(), event.getCategoryName(), event.getDescription(), event.getStartDate()));

        } catch (SQLException e) {
            System.out.println("Add event to DB failed");
            System.out.println(e.getMessage());

        }
    }

    @Override
    public Event find(Integer id) {
        Event event = null;
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events where id = " + Integer.toString(id));

            if(rs.next()) {

                event = new Event(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getString("description"),
                        rs.getString("date")
                );
                event.setId(rs.getInt("id"));
            }

        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }
        return event;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM events");
            while(rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        Category.getCategoryByName(rs.getString("category")),
                        rs.getString("description"),
                        rs.getString("date")
                );
                event.setId(rs.getInt("id"));
                events.add(event);
            }
        } catch(SQLException e) {
            System.out.println("Second connection to DB failed");
            System.out.println(e.getMessage());
        }

        return events;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT category FROM events");
            while(rs.next()) {
                Category category = new Category(
                        rs.getString("category")
                );
                categories.add(category);
            }
            return categories;
        } catch(SQLException e) {
            System.out.println("Second connection to DB failed");
            System.out.println(e.getMessage());
        }

        return categories;
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
                        rs.getString("date")
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



