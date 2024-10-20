package server;

import com.google.gson.Gson;
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
        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    /*
    * Registers new user when /user post is called
    * */
    private String createUser(Request req, Response res) throws Exception{
        var newUser = serializer.fromJson(req.body(), UserData.class);
        var result = UserService.register(newUser);
//        return serializer.toJson(result);
        return "hello";
    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
