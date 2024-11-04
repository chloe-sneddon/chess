package service;

import dataaccess.DataAccessException;
import dataaccess.game.GameDAO;
import model.GameData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
//test because it is loading late

public class GameServiceMemTests extends MemTestsSetUp {

    @Test
    @DisplayName("Normal createGame")
    public void createGame() {
        GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);
        try {
            var authData = registerUser();
            gameService.createGame(authData,gmDataInput);
        } catch (Exception e) {
            Assertions.fail("Unexpected error");
        }
    }

    @Test
    @DisplayName("Bad createGame")
    public void badCreateGame(){
        try{
            registerUser();
            var authData = "incorrect token";
            GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);
            gameService.createGame(authData,gmDataInput);
            Assertions.fail("unexpected succeed with incorrect authToken");
        } catch (Exception e) {}
    }

    @Test
    @DisplayName("Normal List game")
    public void listGames(){
        try{
            var authToken = registerUser();
            ArrayList<GameData> expected = new ArrayList<>();
            GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);

            var gameOneData = gameService.createGame(authToken,gmDataInput);
            var gameTwoData = gameService.createGame(authToken,gmDataInput);
            expected.add(gameTwoData);
            expected.add(gameOneData);

            ArrayList<GameData> activeGames = GameService.listGames(authToken);
            for (GameData game : activeGames){
                if(!expected.contains(game)){
                    Assertions.fail("Not same games");
                }
            }
        } catch (Exception e) {
            Assertions.fail("unexpected error");
        }
    }

    @Test
    @DisplayName("Empty List game")
    public void emptylistGames() {
        try {
            var authToken = registerUser();
            ArrayList<GameData> expected = new ArrayList<>();
            ArrayList<GameData> activeGames = GameService.listGames(authToken);

            for (GameData game : activeGames) {
                if (!expected.contains(game)) {
                    Assertions.fail("Not same games");
                }
            }
        } catch (Exception e) {
            Assertions.fail("unexpected error");
        }
    }

    @Test
    @DisplayName("Normal join game")
    public void joinGame(){
        try{
            String token = registerUser();
            int gameID = gameSetUp(token);
            GameDAO gameMem = gameService.getGameDAO();
            String expected = "usErName";
//            test white
            gameService.joinGame(token,gameID,"WHITE");
            String actualUser = gameMem.getUser(gameID,"WHITE");
            Assertions.assertEquals(expected,actualUser);
//            test black
            gameService.joinGame(token,gameID,"BLACK");
            actualUser = gameMem.getUser(gameID,"BLACK");
            Assertions.assertEquals(expected,actualUser);

        }
        catch (Exception e) {
            Assertions.fail("unexpected error");
        }
    }

    @Test
    @DisplayName("Bad request joinGame")
    public void badJoinGame(){
        try{
            String token = registerUser();
            int expectedGameID = gameSetUp(token);
            int badGameID = 1211;
            GameDAO gameMem = gameService.getGameDAO();

            gameService.joinGame(token,badGameID,"WHITE");
            gameMem.getUser(expectedGameID,"WHITE");
        }
        catch (DataAccessException e) {
            DataAccessException expected = new DataAccessException("Error: bad request",400);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (ServiceException e){
            ServiceException expected = new ServiceException("Error: bad request",400);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Already Taken joinGame")
    public void takenJoinGame(){
        try{
            String token = registerUser();
            int expectedGameID = gameSetUp(token);
            GameDAO gameMem = gameService.getGameDAO();

            gameService.joinGame(token,expectedGameID,"WHITE");
            gameMem.getUser(expectedGameID,"WHITE");
//            test
            gameService.joinGame(token,expectedGameID,"WHITE");
        }
        catch (DataAccessException e){
            Assertions.fail(e.message());
        }
        catch (ServiceException e){
            ServiceException expected = new ServiceException("Error: already taken",403);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
        //   403 { "message": "Error: already taken" }
    }

}
