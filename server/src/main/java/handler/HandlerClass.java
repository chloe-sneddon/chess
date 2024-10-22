package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.GameData;
import model.UserData;
import service.UserService;
import service.GameService;
import service.ServiceException;
import spark.Request;
import spark.Response;
import Response.ListGamesResponse;
import Request.JoinGameRequest;

import java.util.ArrayList;

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
        catch(ServiceException e){
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
        catch (ServiceException e) {
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
        catch(DataAccessException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(ServiceException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(Exception e){
            res.status(500);
            return wrapException(e);
        }
    }

    public String listGames(Request req, Response res){
        try{
            var authToken = req.headers("authorization");
            ArrayList<GameData> activeGames = GameService.listGames(authToken);
            ListGamesResponse listGamesResponse = new ListGamesResponse(activeGames);
            return serializer.toJson(listGamesResponse,ListGamesResponse.class);
        }
        catch(DataAccessException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(ServiceException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(Exception e){
            res.status(500);
            return wrapException(e);
        }
    }

    public String joinGame(Request req, Response res){
        try{
            var authToken = req.headers("authorization");
            JoinGameRequest gmData = serializer.fromJson(req.body(), JoinGameRequest.class);
            int gameID = gmData.gameID();
            String playerColor = gmData.playerColor();
            GameService.joinGame(authToken,gameID,playerColor);
            return "{}";
        }
        catch(DataAccessException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch(ServiceException e){
            res.status(e.statusCode());
            return wrapException(e);
        }
        catch (Exception e) {
            res.status(500);
            return wrapException(e);
        }
//        return null;
    }

    private String wrapException(Exception e){
        return "{\"message\": \"" + e.getLocalizedMessage() + "\"}";
    }

    public static String wrapGameList(ArrayList<GameData> gameList){
//    [200] { "games": [{"gameID": 1234, "whiteUsername":"", "blackUsername":"", "gameName:""}, {"gameID": 1234, "whiteUsername":"", "blackUsername":"", "gameName:""} ]}

        if(gameList.isEmpty()){
            return "{ \"games\": [] }";
        }

        String finalString = "{ \"games\": [";
        for (int i = 0; i < gameList.size(); i++){
            GameData game = gameList.get(i);
            if(i == gameList.size()-1){
                String tmp = game.toString();
                finalString = finalString + tmp + " ]}";
            }
            else{
                String tmp = game.toString();
                finalString = finalString + tmp + ", ";
            }
        }
        return finalString;
    }

}
