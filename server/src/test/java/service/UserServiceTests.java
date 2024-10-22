package service;

import dataaccess.DataAccessException;
import dataaccess.MemoryUserDAO;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;



public class UserServiceTests {
    static UserService userService = new UserService();
    @BeforeEach
    public void run(){
        userService.clear();
    }

    private String registerUser() throws Exception{
            var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
            var regData = userService.register(registerData);
            return regData.authToken();
    }


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
    @DisplayName("Normal Register User Test")
    public void registerUserService() throws Exception{
        AuthData expected = new AuthData ("this is a token", "usErName");
        var registerData = new UserData("usErName","myPsw@rd", "email@email.com");
        var actual = userService.register(registerData);

        Assertions.assertEquals(expected,actual,"Register is true");
        userService.clear();
    }

    @Test
    @DisplayName("Bad Register Input")
    public void badRegisterInput(){
        boolean success = false;
        try {
            registerUser();
        }
        catch(Exception e){
            Assertions.fail("unexpected invalid registration from cleared db");
        }
        try{
            AuthData expected = new AuthData ("this is a token", "usErName");
            var registerData = new UserData("usErName","myPsw@rd", "email@email.com");
            Assertions.fail("Did not throw error");
        }
        catch(Exception e){
            return;
        }

    }

    @Test
    @DisplayName("Normal Login Test")
    public void login(){
        try{
            AuthData expected = new AuthData("this is a token", "usErName");
//        set up
            var userService = new UserService();
            var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
            userService.register(registerData);

//        test
            var myUserData = new UserData("usErName", "myPsw@rd", null);
            var actual = userService.login(myUserData);

            Assertions.assertEquals(expected, actual, "Login is true");
            userService.clear();
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Normal Logout")
    public void logout(){
        try{
            var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
            var authData = userService.register(registerData);
            userService.logout(authData.authToken());
        }
        catch(Exception e){
            Assertions.fail();
        }
    }

}
