package dataaccess;

import model.GameData;
import model.UserData;
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
            String newGame = SqlTestStatements.INSERT_GAME_TEMP;
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

    @Test
    @DisplayName("negative get active games")
    public void negGetGames(){
        try{
            ArrayList<GameData> actual = gameSql.getActiveGames();
            int expected = 0;
            Assertions.assertEquals(expected,actual.size());
        } catch (DataAccessException e) {
            Assertions.fail("Expected Error");
        }
    }

    @Test
    @DisplayName("Join Game")
    public void joinGame(){
        try(var conn = DatabaseManager.getConnection()){
            addData(conn);
            gameSql.joinGame(123,"WHITE","firstUsrname");
            gameSql.joinGame(123,"BLACK","usernameSecond");
            ArrayList<GameData> activeGames = gameSql.getActiveGames();
            GameData game = activeGames.get(0);
            Assertions.assertEquals("firstUsrname",game.whiteUsername());
            Assertions.assertEquals("usernameSecond",game.blackUsername());
        }
        catch(Exception e){
            Assertions.fail("Unexpected error: "+ e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Negative join game")
    public void negJoin(){
        try{
            gameSql.joinGame(123,"WHITE","firstUsrname");
            Assertions.fail("expected error thrown");
        }
        catch(Exception e){
            System.out.println("Passed thrown exception");
        }
    }

    @Test
    @DisplayName("insert user")
    public void insertUser(){
        try{
            UserData data = new UserData("Puddles", "123Pass#wordHas#", "email@email.com");
            usrSql.insertUser(data);
            String pw = usrSql.getPassword("Puddles");
            Assertions.assertEquals("123Pass#wordHas#",pw);
        }
        catch (DataAccessException e) {
            Assertions.fail("Unexpected error: " + e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("negative insert user")
    public void negInsertUser(){
        try(var conn = DatabaseManager.getConnection()){
            addData(conn);
            UserData data = new UserData("Puddles", "123Pass#wordHas#", "email@email.com");
            usrSql.insertUser(data);
            Assertions.fail("expected failed error");
        }
        catch (Exception e) {
            System.out.println("Passed thrown error");
        }
    }
}
