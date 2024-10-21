package service;

import dataaccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;



public class UserServiceTests {

    @Test
    @DisplayName("Register User DAO Test")
    public void registerUserDAO(){
        var dataAccess = new MemoryUserDAO();
        var expected = new UserData("usErName","myPsw@rd", "email@email.com");
        var actual = dataAccess.getUser("a");
        Assertions.assertNotEquals(expected,actual,"Not in memory");

        dataAccess.insertUser(expected);
        actual = dataAccess.getUser("usErName");
        Assertions.assertEquals(expected, actual, "User In Memory");
        dataAccess.clear();
    }

    @Test
    @DisplayName("Register User Test")
    public void registerUserService() throws Exception{
        AuthData expected = new AuthData ("this is a token", "usErName");
        var userService = new UserService();
        var registerData = new UserData("usErName","myPsw@rd", "email@email.com");
        var actual = userService.register(registerData);

        Assertions.assertEquals(expected,actual,"Login is true");
        userService.clear();
    }

    @Test
    @DisplayName("Login Test")
    public void login() throws Exception{
        AuthData expected = new AuthData ("this is a token", "usErName");
//        set up
        var userService = new UserService();
        var registerData = new UserData("usErName","myPsw@rd", "email@email.com");
        userService.register(registerData);

//        test
        var myUserData = new UserData("usErName","myPsw@rd", null);
        var actual = userService.login(myUserData);

        Assertions.assertEquals(expected,actual,"Login is true");
        userService.clear();
    }
}
