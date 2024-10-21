package handler;

import com.google.gson.Gson;
import model.UserData;
import service.UserService;
import spark.Request;
import spark.Response;

public class UserHandler {
    private final Gson serializer = new Gson();

    public String createUser(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.register(newUser);
            return serializer.toJson(result);
        }
        catch (Exception e){
            res.status(500);
            return serializer.toJson(e);
        }
    }
    public String login(Request req, Response res){
        try {
            var newUser = serializer.fromJson(req.body(), UserData.class);
            var result = UserService.login(newUser);
            return serializer.toJson(result);
        }
        catch (Exception e){
            res.status(500);
            return serializer.toJson(e);
        }
    }

    public String clear(Request req, Response res) {
        try{
            UserService.clear();
            return serializer.toJson("");
        }
        catch(Exception e){
            res.status(500);
            return serializer.toJson(e);
        }
    }
}
