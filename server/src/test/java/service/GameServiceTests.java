package service;

import dataaccess.MemoryUserDAO;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static service.UserServiceTests.userService;

public class GameServiceTests {
    static GameService gameService = new GameService();

    @BeforeEach
    public void run() {
        GeneralService.clear();
    }

    private String registerUser() throws Exception {
        var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
        var regData = userService.register(registerData);
        return regData.authToken();
    }

    private int gameSetUp() throws Exception{
        GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);
        var authData = registerUser();
        var gameData = gameService.createGame(authData,gmDataInput);
        return gameData.gameID();
    }

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
            gameSetUp();
            gameService.listGames(authToken);
        } catch (Exception e) {
            Assertions.fail("unexpected error");
        }

    }

}
