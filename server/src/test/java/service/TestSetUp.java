package service;

import model.GameData;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;

import static service.UserServiceTests.userService;

public class TestSetUp {
    static GameService gameService = new GameService();

    @BeforeEach
    public void run() {
        GeneralService.clear();
    }

    public String registerUser() throws Exception {
        var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
        var regData = userService.register(registerData);
        return regData.authToken();
    }

    public int gameSetUp(String authToken) throws Exception{
        GameData gmDataInput = new GameData(0,null,null,"gameNAmed",null);
        var gameData = gameService.createGame(authToken,gmDataInput);
        return gameData.gameID();
    }
}
