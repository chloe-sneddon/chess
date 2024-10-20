package service;

import com.google.gson.Gson;
import dataaccess.MemoryAuthDOA;
import dataaccess.MemoryGameDOA;
import dataaccess.MemoryUserDOA;

// Handles basic requests common to user, game, and auth service
//is this an HTTP handler class
public class GeneralService {
    public final static MemoryUserDOA memUsrData = new MemoryUserDOA();
    public final static MemoryAuthDOA memAuthData = new MemoryAuthDOA();
    public final static MemoryGameDOA memGameData = new MemoryGameDOA();
//    Verify authToken
//    deserializing and re-serializing gson objects
//    clear db / memory layer
}
