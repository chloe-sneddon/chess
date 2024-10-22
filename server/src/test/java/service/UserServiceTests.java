package service;

import dataaccess.MemoryUserDAO;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;



public class UserServiceTests {
    static UserService userService = new UserService();

    @BeforeEach
    public void run() {
        userService.clear();
    }

    private String registerUser() throws Exception {
        var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
        var regData = userService.register(registerData);
        return regData.authToken();
    }

    @Test
    @DisplayName("Register User DAO Test")
    public void registerUserDAO() {
        var dataAccess = new MemoryUserDAO();
        var expected = new UserData("usErName", "myPsw@rd", "email@email.com");
        var actual = dataAccess.getUser("a");
        Assertions.assertNotEquals(expected, actual, "Not in memory");

        dataAccess.insertUser(expected);
        actual = dataAccess.getUser("usErName");
        Assertions.assertEquals(expected, actual, "User In Memory");
        dataAccess.clear();
    }

    @Test
    @DisplayName("Normal Register User Test")
    public void registerUserService() throws Exception {
        try{
            registerUser();
        } catch (Exception e) {
            Assertions.fail("Unexpected Error");
        }
    }

    @Test
    @DisplayName("Bad Register Input")
    public void badRegisterInput() {
        try {
            registerUser();
        } catch (Exception e) {
            Assertions.fail("unexpected invalid registration from cleared db");
        }
        try {
            registerUser();
            Assertions.fail("No Error thrown");
        } catch (Exception e) {
        }

    }

    @Test
    @DisplayName("Normal Login Test")
    public void login() {
        try {
//        set up
            var userService = new UserService();
            UserData registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
            userService.register(registerData);

//        test
            UserData myUserData = new UserData("usErName", "myPsw@rd", null);
            userService.login(myUserData);
        }
        catch (Exception e) {
            Assertions.fail("Unexpected Error");
        }
    }

    @Test
    @DisplayName("Normal Logout")
    public void logout() {
        try {
            var registerData = new UserData("usErName", "myPsw@rd", "email@email.com");
            var authData = userService.register(registerData);
            userService.logout(authData.authToken());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Bad login")
    public void badLogin(){
        try{
            registerUser();
        } catch (Exception e) {
            Assertions.fail("Invalid register");
        }
        try{
            var myUserData = new UserData("usErName", "wrongPassword", null);
            userService.login(myUserData);
            Assertions.fail("Logged in with invalid credentials");
        }
        catch(Exception e){}
    }

    @Test
    @DisplayName("Bad Logout")
    public void badLogout(){
        try{
           registerUser();
           userService.logout("wrongToken");
           Assertions.fail("No error thrown");
        } catch (Exception e) {}
    }
}
