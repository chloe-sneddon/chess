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
        catch (DataAccessException dta){
            res.status(dta.statusCode());
            return serializer.toJson(dta.message());
        }
        catch(UserServiceException userE){
            res.status(userE.statusCode());
            return serializer.toJson(userE.message());
        }
        catch (Exception e){
            res.status(500);
            return serializer.toJson(e.getLocalizedMessage());
        }
    }

    public String login(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.login(newUser);
            return serializer.toJson(result);
        }
        catch (DataAccessException dta){
            res.status(dta.statusCode());
            return serializer.toJson(dta.message());
        }
        catch (Exception e){
            res.status(500);
            return serializer.toJson(e.getLocalizedMessage());
        }
    }

    public String clear(Request req, Response res) {
        try{
            UserService.clear();
            return serializer.toJson("");
        }
        catch(Exception e){
            res.status(500);
            return serializer.toJson(e.getLocalizedMessage());
        }
    }

    public String logout(Request req, Response res) {
        try{
            UserService.logout(req.body());
            return serializer.toJson("");
        }
        catch(Exception e){
            res.status(500);
            return serializer.toJson(e.getLocalizedMessage());
        }
    }

//    public String createGame(Request req, Response res){
//        try{
//            var newGame = serializer.fromJson(req.body(), GameData.class);
//            var result = GameService.createGame(newGame);
//            return serializer.toJson(result);
//        }
//        catch(Exception e){
//            return serializer.toJson(e.getLocalizedMessage());
//        }
//    }

}
