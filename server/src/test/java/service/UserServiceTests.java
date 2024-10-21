package service;

import dataaccess.MemoryUserDAO;
import model.UserData;
import org.junit.jupiter.api.Test;

public class UserServiceTests {
    private Object Assertions;

    @Test
    public void registerUser(){
        var dataAccess = new MemoryUserDAO();
        var expected = new UserData("usErName","myPsw@rd", "email@email.com");
        var actual = dataAccess.getUser("a");
        Assertions.assertEquals(expected,actual);
    }
}
