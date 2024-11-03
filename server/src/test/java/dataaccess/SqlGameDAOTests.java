package dataaccess;

import chess.ChessGame;
import dataaccess.gameDAO.GameSqlAccess;
import dataaccess.gameDAO.GameUpdate;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SqlGameDAOTests extends SqlTestFunctions{

    @Test
    @DisplayName("createGame")
    public void createGame(){
        try{
            String gameName = "bestGame";
            gameSql.createGame(gameName);
            String statement = "Select gameName from gameData;";

            try (var conn = DatabaseManager.getConnection()) {
                try (var actualToken = conn.prepareStatement(statement)) {
                    var rs = actualToken.executeQuery();
                    rs.next();
                    var result = rs.getString(1);

                    if(!(gameName.equals(result))){
                        throw new Exception("authToken not equal");
                    }
                }
            }
        }
        catch(Exception e){
            Assertions.fail("Unexpected Exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("get gameData")
    public void gameData(){
        try(var conn = DatabaseManager.getConnection()){
            addData(conn);
            gameSql.getGameData(123);
        }
        catch(Exception e){
            Assertions.fail("unexpected exception: "+ e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("negative get gameData")
    public void negGameData(){
        try(var conn = DatabaseManager.getConnection()){
            gameSql.getGameData(123);
            Assertions.fail("Error not thrown");
        }
        catch(Exception e){
            System.out.println("Passed failed test");
        }
    }

    @Test
    @DisplayName("get active games")
    public void activeGames(){
        try(var conn = DatabaseManager.getConnection()){
            addData(conn);
            String newGame = SqlTestStatements.insertGameTemp;
            String[] gameNames = {"first","second","Third"};
            for (int i = 0; i < 3; i++){
                try(var statement = conn.prepareStatement(newGame)){
                    statement.setInt(1,i);
                    statement.setString(2,gameNames[i]);
                    statement.executeUpdate();
                }
            }
            ArrayList<GameData> actual = gameSql.getActiveGames();
            int expectedSize = 4;
            Assertions.assertEquals(expectedSize,actual.size());
        } catch (Exception e) {
            Assertions.fail("Unexpected error: " + e.getLocalizedMessage());
        }

    }

}
