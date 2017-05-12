package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {

    public static Connection connection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/events.db");
        } catch (SQLException e) {
            System.out.println("Connection to DB failed");
        }

        return connection;
    }

    public static void createTables() throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS events\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT NOT NULL,\n" +
                "    category TEXT,\n" +
                "    description TEXT,\n" +
                "    date TEXT\n" +
                ")");
    }

}