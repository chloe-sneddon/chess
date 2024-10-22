package server;

import handler.HandlerClass;
import spark.*;

public class Server {
//    private final Gson serializer = new Gson();

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");
        // Register your endpoints and handle exceptions here.
        Spark.post("/user", this::createUser);
        Spark.post("/session", this::login);
        Spark.delete("/db", this::clear);
        Spark.delete("/session", this::logout);
        Spark.post("/game",this::createGame);
        Spark.get("/game",this::listGame);
        Spark.put("/game",this::joinGame);

        //This line initializes the server and can be removed once you have a functioning endpoint 
//        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private String createUser(Request req, Response res){
        HandlerClass handler = new HandlerClass();
        return handler.createUser(req,res);
    }

    private String login(Request req, Response res){
        HandlerClass handler = new HandlerClass();
        return handler.login(req,res);
    }

    private String clear(Request req, Response res){
        HandlerClass usrHandler = new HandlerClass();
        return usrHandler.clear(req,res);
    }

    private String logout(Request req, Response res){
        HandlerClass usrHandler = new HandlerClass();
        return usrHandler.logout(req,res);
    }

    private String createGame(Request req, Response res){
        HandlerClass gameHandler = new HandlerClass();
        return gameHandler.createGame(req,res);
    }

    private String listGame(Request req, Response res){
        HandlerClass gameHandler = new HandlerClass();
        return gameHandler.listGames(req,res);
    }

    private String joinGame(Request req, Response res){
        HandlerClass gameHandler = new HandlerClass();
        return gameHandler.joinGame(req,res);
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
