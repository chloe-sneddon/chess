package service;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

//import static UserServiceTests.userService;

public class MemTestsSetUp {
    static GameService gameService = new GameService();
    static UserService userService = new UserService();

    @BeforeEach
    public void run() throws DataAccessException{
        DatabaseManager.configureDatabase();
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

    public void dataAccessAssertion(DataAccessException e, DataAccessException expected){
        Assertions.assertEquals(expected.message(),e.message());
        Assertions.assertEquals(expected.statusCode(),e.statusCode());
    }

}
