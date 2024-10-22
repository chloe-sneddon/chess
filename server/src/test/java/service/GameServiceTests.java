package service;

import dataaccess.MemoryUserDAO;
import handler.HandlerClass;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

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

    private int gameSetUp(String authToken) throws Exception{
        GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);
        var gameData = gameService.createGame(authToken,gmDataInput);
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

}
