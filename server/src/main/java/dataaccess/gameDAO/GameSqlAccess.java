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

    public GameData getGameData(int gameID) throws DataAccessException{
        String getGameData = SqlSyntax.getGameData;
        try(var conn = DatabaseManager.getConnection()){
            try(var statement = conn.prepareStatement(getGameData)) {
                statement.setInt(1,gameID);
                var rs = statement.executeQuery();
                rs.next();
                gameID = rs.getInt(1);
                String blkUser =  rs.getString(2);
                String whtUser =  rs.getString(3);
                String gameName =  rs.getString(4);
                String jasonGame =  rs.getString(5);

                ChessGame game = gmSerializer.deserializeGame(jasonGame);

                GameData returnVar = new GameData(gameID,whtUser,blkUser,gameName,game);

                return returnVar;
            }
        }
        catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage(),500);
        }
    }

    public ArrayList<GameData> getActiveGames() throws DataAccessException{

        ArrayList<GameData> activeGames = new ArrayList<>();
        String getActiveGames = SqlSyntax.getActiveGames;

        try(var conn = DatabaseManager.getConnection()){
            try(var statement = conn.prepareStatement(getActiveGames)){
                var rs = statement.executeQuery();

                while(rs.next()){

                    int gameID = rs.getInt(1);
                    String blkUser =  rs.getString(2);
                    String whtUser =  rs.getString(3);
                    String gameName =  rs.getString(4);
                    String jasonGame =  rs.getString(5);
                    ChessGame game = gmSerializer.deserializeGame(jasonGame);

                    GameData tmp = new GameData(gameID,whtUser,blkUser,gameName,game);
                    activeGames.add(tmp);
                }

            }
            return activeGames;
        }
        catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage(),500);
        }
    }

    public String getUser(int gameID, String playerColor) throws DataAccessException{
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
    public void joinGame(int gameID, String playerColor, String username) throws DataAccessException{}
}
