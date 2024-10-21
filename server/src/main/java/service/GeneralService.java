package service;

import dataaccess.*;

// Handles basic requests common to user, game, and auth service
//is this an HTTP handler class
public class GeneralService {
    public final static UserDAO usrData = new MemoryUserDAO();
    public final static AuthDAO authData = new MemoryAuthDAO();
    public final static GameDAO gameData = new MemoryGameDAO();

    public UserDAO getUserDAO(){
        return usrData;
    }
    public AuthDAO getAuthDAO(){
        return authData;
    }
    public GameDAO getGameDAO(){
        return gameData;
    }


//    Verify authToken
//    deserializing and re-serializing gson objects
//    clear db / memory layer
}
