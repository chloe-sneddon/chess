package dataaccess;

import model.GameData;
import service.ServiceException;
import java.util.ArrayList;

/*
 * Data Access Object Interface for Game Data (model.GameData)
 */
public interface GameDAO {

    void clear();
    int createGame(String gameName) throws ServiceException;
    GameData getGameData(int gameID) throws DataAccessException;
    ArrayList<GameData> getActiveGames();
    String getUser(int gameID, String playerColor) throws DataAccessException;
    void joinGame(int gameID, String playerColor, String username) throws DataAccessException;

}
