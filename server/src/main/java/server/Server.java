package server;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import handler.HandlerClass;
import server.websocket.WebSocketHandler;
import spark.*;
import spark.Spark;

public class Server {

    private final HandlerClass handler = new HandlerClass();
    private final WebSocketHandler wbHandler = new WebSocketHandler();

    public int run(int desiredPort) {
        Spark.port(desiredPort);
        Spark.staticFiles.location("web");

        Spark.webSocket("/ws", wbHandler);

        try{
            DatabaseManager.configureDatabase();
        }
        catch (Throwable ex) {
            System.out.printf("Unable to start server: %s%n", ex.getMessage());
        }

        Spark.post("/user", this::createUser);
        Spark.post("/session", this::login);
        Spark.delete("/db", this::clear);
        Spark.delete("/session", this::logout);
        Spark.post("/game",this::createGame);
        Spark.get("/game",this::listGame);
        Spark.put("/game",this::joinGame);
//        Spark.post("/ws",this::webSocket);

        Spark.awaitInitialization();

        return Spark.port();
    }

    private String webSocket(Request req, Response res){
//        wbHandler.
//        wbHandler.onMessage(session,req);
        System.out.print("webSocket Connection Made");
        return "test";
    }
    private String createUser(Request req, Response res){
        return handler.createUser(req,res);
    }

    private String login(Request req, Response res){
        return handler.login(req,res);
    }

    private String clear(Request req, Response res){
        return handler.clear(req,res);
    }

    private String logout(Request req, Response res){
        return handler.logout(req,res);
    }

    private String createGame(Request req, Response res){
        return handler.createGame(req,res);
    }

    private String listGame(Request req, Response res){
        return handler.listGames(req,res);
    }

    private String joinGame(Request req, Response res){
        return handler.joinGame(req,res);
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

}
