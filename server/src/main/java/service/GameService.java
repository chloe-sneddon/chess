package service;

import dataaccess.DataAccessException;
import model.GameData;

public class GameService extends GeneralService{

    public static GameData createGame(String authToken, GameData gmData) throws ServiceException, DataAccessException {
        verifyToken(authToken);
        int gameID = gameData.createGame(gmData.gameName());
        return gameData.getGameData(gameID);
    }
    public static GameData listGames (String authToken) throws DataAccessException, ServiceException{
        verifyToken(authToken);
//        [200] { "games": [{"gameID": 1234, "whiteUsername":"", "blackUsername":"", "gameName:""} ]}
        return null;
        }
}
