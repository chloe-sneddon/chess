package service;

import dataaccess.*;

// Handles basic requests common to user, game, and auth service
//is this an HTTP handler class
public class GeneralService {
    public final static UserDAO UsrData = new MemoryUserDAO();
    public final static AuthDAO memAuthData = new MemoryAuthDAO();
    public final static GameDAO memGameData = new MemoryGameDAO();


//    Verify authToken
//    deserializing and re-serializing gson objects
//    clear db / memory layer
}
