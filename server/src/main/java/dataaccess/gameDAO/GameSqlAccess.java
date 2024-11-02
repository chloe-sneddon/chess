package dataaccess.gameDAO;

import chess.ChessGame;
import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.SqlSyntax;
import model.GameData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class GameSqlAccess implements GameDAO{
    GameUpdate gmSerializer = new GameUpdate();

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

    public int createGame(String gameName) throws DataAccessException{
        int gameID = createGameID();
        ChessGame game = new ChessGame();
        String jsonGame = gmSerializer.serialize(game);
        String createGame = SqlSyntax.createGame;

        try(var conn = DatabaseManager.getConnection()){
            try(var statement = conn.prepareStatement(createGame)) {
                statement.setInt(1,gameID);
                statement.setString(2,gameName);
                statement.setString(3,jsonGame);
                statement.executeUpdate();
                return gameID;
            }
        }
        catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage(),500);
        }
    }

    private int createGameID(){
        Random rand = new Random();
        int gameId = rand.nextInt(1000);
        return gameId;
    }

    public GameData getGameData(int gameID) throws DataAccessException{return null;}
    public ArrayList<GameData> getActiveGames(){return null;}
    public String getUser(int gameID, String playerColor) throws DataAccessException{return null;}
    public void joinGame(int gameID, String playerColor, String username) throws DataAccessException{}
}
