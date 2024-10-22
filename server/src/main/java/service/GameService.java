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
    public static GameData joinGame(String authToken, GameData gmData) throws ServiceException, DataAccessException{
        verifyToken(authToken);

    }
}
