package dao;

import model.Category;
import model.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventDaoSqlite implements EventDao{

    @Override
    public void add(Event event) {
        
    }

    @Override
    public Event find(int id) {
        Event event = null;
        try {
            Connection connection = JDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events where id = " + Integer.toString(id));

            if(rs.next()) {

                event = new Event(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getString("description"),
                        new Date(rs.getString("date"))
                );
                event.setId(rs.getInt("id"));
            }

        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return event;
    }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Event> getAll() {
        List<Event> products = new ArrayList<Event>();

        try {
            Connection connection = JDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while(rs.next()) {
                Event product = new Event(
                        rs.getString("name"),
                        new Category(rs.getString("category")),
                        rs.getString("description"),
                        new Date(rs.getString("date"))
                );
                products.add(product);
            }
        } catch(SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return products;
    }
    }

    @Override
    public List<Event> getBy(String category) {
        return null;
    }
}
