package service;

import dataaccess.DataAccessException;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static service.GameServiceTests.gameService;
import static service.UserServiceTests.userService;

public class GeneralServiceTests {
    @BeforeEach
    public void run() {
        userService.clear();
    }

    private String registerUser() throws Exception {
        var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
        var regData = userService.register(registerData);
        return regData.authToken();
    }

    private int gameSetUp(String authToken) throws Exception{
        GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);
        var gameData = gameService.createGame(authToken,gmDataInput);
        return gameData.gameID();
    }

    @Test
    @DisplayName("clear auth")
    public void clearAuth() {
        try{
            var token = registerUser();
            gameSetUp(token);
            var authDAO = GeneralService.getAuthDAO();

            GeneralService.clear();
            authDAO.getUsername(token);
            Assertions.fail("Error: authDAO was not cleared");
        }
        catch(DataAccessException e){
            DataAccessException expected = new DataAccessException("Error: unauthorized",401);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("clear user")
    public void clearUser() {
        try{
            var token = registerUser();
            gameSetUp(token);
            var dao = GeneralService.getUserDAO();

            GeneralService.clear();
            dao.getPassword("usErName");
            Assertions.fail("Error: userDAO was not cleared");
        }
        catch(DataAccessException e){
            DataAccessException expected = new DataAccessException("Error: bad request",400);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("clear game")
    public void clearGame() {
        try{
            var token = registerUser();
            var gameID = gameSetUp(token);
            var dao = GeneralService.getGameDAO();
            GeneralService.clear();

            dao.getGameData(gameID);
            Assertions.fail("Error: gameDAO was not cleared");
        }
        catch(DataAccessException e){
            DataAccessException expected = new DataAccessException("Error: bad request",400);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("verify token")
    public void verifyToken(){
        try{
            var token = registerUser();
            GeneralService.verifyToken(token);
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("verify invalid token")
    public void verifyInvalidToken(){
        try{
            var token = "unexpectedToken";
            registerUser();
            GeneralService.verifyToken(token);
        }
        catch(DataAccessException e){
            DataAccessException expected =  new DataAccessException("Error: unauthorized", 401);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

}
