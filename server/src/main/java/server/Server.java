package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;
import service.UserService;
import spark.*;

public class Server {
    private final Gson serializer = new Gson();

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
//        Spark.post("/user", this::createUser);
        // Register your endpoints and handle exceptions here.
        Spark.post("/user", this::createUser);
        Spark.post("/session", this::login);

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private String createUser(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.register(newUser);
            return serializer.toJson(result);
        }
        catch (Exception e){
            res.status(500);
            return serializer.toJson(e);
        }
//        res.status()
    }

    private String login(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.login(newUser);
            return serializer.toJson(result);
        }
        catch (Exception e){
            return serializer.toJson(e);
        }
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
