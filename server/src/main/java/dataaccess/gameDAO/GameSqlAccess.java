package dataaccess.gameDAO;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import model.GameData;
import service.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameSqlAccess implements GameDAO{
    public void clear() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            String sqlClearDB = "TRUNCATE gameData";

            try(var preparedClear = conn.prepareStatement(sqlClearDB)){
                preparedClear.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new DataAccessException(String.format("Unable to get connection: %s", ex.getMessage()),500);
        }
    }
    public int createGame(String gameName) throws ServiceException{return 0;}
    public GameData getGameData(int gameID) throws DataAccessException{return null;}
    public ArrayList<GameData> getActiveGames(){return null;}
    public String getUser(int gameID, String playerColor) throws DataAccessException{return null;}
    public void joinGame(int gameID, String playerColor, String username) throws DataAccessException{}
}
