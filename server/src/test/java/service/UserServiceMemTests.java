package service;

import dataaccess.DataAccessException;
import model.UserData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class UserServiceMemTests extends MemTestsSetUp {

    @Test
    @DisplayName("Normal Register User Test")
    public void registerUserService() throws Exception {
        try{
            registerUser();
        }
        catch (Exception e) {
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Bad Register Input")
    public void badRegisterInput() {
        try {
            registerUser();
        }
        catch (Exception e) {
            Assertions.fail(e.getLocalizedMessage());
        }

        try {
            registerUser();
            Assertions.fail("No Error thrown");
        }
        catch(DataAccessException e){
            Assertions.fail(e.message());
        }
        catch(ServiceException e){
            DataAccessException expected =  new DataAccessException("Error: already taken",403);
            DataAccessException actual = new DataAccessException(e.message,e.errorCode);
            dataAccessAssertion(actual,expected);
        }
        catch (Exception e) {
            Assertions.fail(e.getLocalizedMessage());
        }
    }


    @Test
    @DisplayName("Normal Login Test")
    public void login() {
        try {
            registerUser();
            UserData myUserData = new UserData("usErName", "myPsw@rd", null);
            userService.login(myUserData);
        }
        catch (Exception e) {
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Normal Logout")
    public void logout() throws Exception{
        var authToken = registerUser();
        userService.logout(authToken);

    }

    @Test
    @DisplayName("Bad login")
    public void badLogin(){
        try{
            registerUser();
        }
        catch (Exception e) {
            Assertions.fail("Invalid register");
        }
        try{
            var myUserData = new UserData("usErName", "wrongPassword", null);
            userService.login(myUserData);
        }
        catch(DataAccessException e){
           Assertions.fail(e.message());
        }
        catch (ServiceException e){
            DataAccessException expected =  new DataAccessException("Error: unauthorized",401);
            DataAccessException actual = new DataAccessException(e.message,e.errorCode);
            dataAccessAssertion(actual,expected);
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Bad Logout")
    public void badLogout(){
        try{
           registerUser();
           userService.logout("wrongToken");
           Assertions.fail("Error: logout permitted from invaild login");
        }
        catch (DataAccessException e){
            DataAccessException expected = new DataAccessException("Error: unauthorized",401);
            Assertions.assertEquals(expected.message(),e.message());
            Assertions.assertEquals(expected.statusCode(),e.statusCode());
        }
        catch (Exception e) {
            Assertions.fail(e.getLocalizedMessage());
        }
    }
}
