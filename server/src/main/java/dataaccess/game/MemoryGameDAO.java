package dataaccess.game;

import chess.ChessGame;
import dataaccess.DataAccessException;
import model.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MemoryGameDAO implements GameDAO {
    private HashMap<Integer, GameData> allGameData = new HashMap <>();

    public void clear() { allGameData.clear(); }

    public int createGame(String gameName) throws DataAccessException {

        if((gameName == null)){
            throw new DataAccessException("Error: no provided gameName",500);
        }

        var gameId = createGameID();
        GameData gmData = new GameData(gameId,null,null,gameName,new ChessGame());
        allGameData.put(gameId,gmData);
        return gmData.gameID();
    }

    private int createGameID(){
        Random rand = new Random();
        int gameId = rand.nextInt(1000);
        return gameId;
    }

    public GameData getGameData(int gameID) throws DataAccessException {
        if(allGameData.isEmpty()){
            throw new DataAccessException("Error: bad request",400);
        }
        return allGameData.get(gameID);
    }

    public String getUser(int gameID, String playerColor) throws DataAccessException {
        var targetGame = getGameData(gameID);

        if((targetGame == null)|(playerColor == null)){
            throw new DataAccessException("Error: bad request", 400);
        }
        
        if (playerColor.equals("WHITE")) {
            String username = targetGame.whiteUsername();
            if ((username == null)) {
                throw new DataAccessException("Error: no white user", 500);
            }
            return username;
        }
        else if (playerColor.equals("BLACK")) {
            String username = targetGame.blackUsername();
            if (username == null) {
                throw new DataAccessException("Error: no black user", 500);
            }
            return username;
        }
        else{
            throw new DataAccessException("Error: bad request", 400);
        }
    }

    public ArrayList<GameData> getActiveGames(){
        ArrayList<GameData> activeGames = new ArrayList<>();
        activeGames.addAll(allGameData.values());
        return activeGames;
    }

    public void joinGame(int gameID, String playerColor, String username) throws DataAccessException{
        GameData joinGame = getGameData(gameID);
        if(joinGame == null){
            throw new DataAccessException("Error: bad request", 400);
        }
        String whiteUsername = joinGame.whiteUsername();
        String blackUsername = joinGame.blackUsername();
        String gameName = joinGame.gameName();
        ChessGame game = joinGame.game();

        if(playerColor == null){
            throw new DataAccessException("Error: bad request", 400);
        }

        if(playerColor.equals("WHITE")){
            GameData joinedGame = new GameData(gameID, username, blackUsername,gameName,game);
            allGameData.put(gameID,joinedGame);
        }
        else if(playerColor.equals("BLACK")){
            GameData joinedGame = new GameData(gameID, whiteUsername, username,gameName,game);
            allGameData.put(gameID,joinedGame);
        }
        else{
            throw new DataAccessException("Error: bad request", 400);
        }
    }
}
