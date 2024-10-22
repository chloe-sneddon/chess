package service;

import dataaccess.DataAccessException;
import model.GameData;

import java.util.ArrayList;

public class GameService extends GeneralService{

    public static GameData createGame(String authToken, GameData gmData) throws ServiceException, DataAccessException {
        verifyToken(authToken);
        int gameID = gameData.createGame(gmData.gameName());
        return gameData.getGameData(gameID);
    }

    public static ArrayList<GameData> listGames (String authToken) throws ServiceException, DataAccessException{
        verifyToken(authToken);
        return gameData.getActiveGames();
    }

    public static void joinGame(String authToken, int gameID,String playerColor) throws ServiceException, DataAccessException{
        if(gameID == 0){
            throw new ServiceException("Error: gameID is not initialized",400);
        }
        if(playerColor == null){
            throw new ServiceException("Error: bad request", 400);
        }
        if(!((playerColor.equals("WHITE")) | (playerColor.equals("BLACK")))){
            throw new ServiceException("Error: bad request", 400);
        }

        verifyToken(authToken);
        String playerUsername = authData.getUsername(authToken);
        colorAvailable(gameID, playerColor);

        gameData.joinGame(gameID,playerColor,playerUsername);
    }

    static private void colorAvailable(int gameID, String playerColor) throws ServiceException{
        try{
            gameData.getUser(gameID,playerColor);
            throw new ServiceException("Error: already taken",403);
        }
        catch(DataAccessException e){
//          user Color is empty
        }
    }
}
