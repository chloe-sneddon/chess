package server;

import handler.UserHandler;
import spark.*;

public class Server {
//    private final Gson serializer = new Gson();

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        // Register your endpoints and handle exceptions here.
        Spark.post("/user", this::createUser);
        Spark.post("/session", this::login);

        //This line initializes the server and can be removed once you have a functioning endpoint 
//        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private String createUser(Request req, Response res){
        UserHandler handler = new UserHandler();
        return handler.createUser(req,res);
    }

    private String login(Request req, Response res){
        UserHandler handler = new UserHandler();
        return handler.login(req,res);
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
