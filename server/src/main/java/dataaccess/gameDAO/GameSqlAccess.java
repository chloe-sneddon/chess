package dataaccess.gameDAO;

import dataaccess.DataAccessException;
import model.GameData;
import service.ServiceException;

import java.util.ArrayList;

public class GameSqlAccess implements GameDAO{
    public void clear() {}
    public int createGame(String gameName) throws ServiceException{return 0;}
    public GameData getGameData(int gameID) throws DataAccessException{return null;}
    public ArrayList<GameData> getActiveGames(){return null;}
    public String getUser(int gameID, String playerColor) throws DataAccessException{return null;}
    public void joinGame(int gameID, String playerColor, String username) throws DataAccessException{}
}
