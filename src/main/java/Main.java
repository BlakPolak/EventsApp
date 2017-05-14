import  static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import controller.EventController;
import dao.SqliteJDBCConnector;
import java.sql.SQLException;



public class Main {
    public static ThymeleafTemplateEngine thymeEngine = new ThymeleafTemplateEngine();

    public static void main(String[] args) {
        try {
            SqliteJDBCConnector.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        EventController controller = new EventController();

        get("/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(controller.showEvents());
        });


        get("/details/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(controller.getEvent(Integer.parseInt(req.params(":id"))));
        });

        get("/create_event", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(controller.createEvent(req, res));
        });
    }


}