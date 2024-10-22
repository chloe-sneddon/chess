package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.GameData;
import model.UserData;
import service.UserService;
import service.GameService;
import service.UserServiceException;
import spark.Request;
import spark.Response;

public class HandlerClass {
    private final Gson serializer = new Gson();

    public String createUser(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.register(newUser);
            return serializer.toJson(result);
        }
        catch (DataAccessException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(UserServiceException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch (Exception e) {
            res.status(500);
            return wrapException(e);
        }
    }

    public String login(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.login(newUser);
            return serializer.toJson(result);
        }
        catch (DataAccessException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch (UserServiceException e) {
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch (Exception e){
            res.status(500);
            return wrapException(e);
        }
    }

    public String clear(Request req, Response res) {
        try{
            UserService.clear();
            return "{}";
        }
        catch(Exception e){
            res.status(500);
            return wrapException(e);
        }
    }

    public String logout(Request req, Response res) {
        try{
            UserService.logout(req.headers("authorization"));
            return "{}";
        }
        catch(DataAccessException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(Exception e){
            res.status(500);
            return wrapException(e);
        }
    }

    public String createGame(Request req, Response res){
        try{
            var authToken = req.headers("authorization");
            GameData newGame = serializer.fromJson(req.body(), GameData.class);

            GameData result = GameService.createGame(authToken,newGame);
            return serializer.toJson(result);
        }
        catch(Exception e){
            return wrapException(e);
        }
    }

    private String wrapException(Exception e){
        return "{\"message\": \"" + e.getLocalizedMessage() + "\"}";
    }

    private String createGameWrapper(int gameID){
        return "{ \"gameID\": " + Integer.toString(gameID) + "}";
    }
}
