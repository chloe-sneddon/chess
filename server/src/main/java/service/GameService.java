package service;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.GameData;

public class GameService extends GeneralService{

    public static GameData createGame(String authToken, GameData gmData) throws UserServiceException, DataAccessException {
        verifyToken(authToken);
        int gameID = gameData.createGame(gmData.gameName());
        return gameData.getGameData(gameID);
    }
}
