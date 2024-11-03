package dataaccess.gameDAO;

import dataaccess.DataAccessException;
import model.GameData;
import service.ServiceException;
import java.util.ArrayList;

/*
 * Data Access Object Interface for Game Data (model.GameData)
 */
public interface GameDAO {

    void clear() throws DataAccessException;
    int createGame(String gameName) throws DataAccessException;
    GameData getGameData(int gameID) throws DataAccessException;
    ArrayList<GameData> getActiveGames() throws DataAccessException;
    String getUser(int gameID, String playerColor) throws DataAccessException;
    void joinGame(int gameID, String playerColor, String username) throws DataAccessException;

}
