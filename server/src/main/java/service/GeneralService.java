package service;

import dataaccess.*;

public class GeneralService {
    public final static UserDAO usrData = new MemoryUserDAO();
    public final static AuthDAO authData = new MemoryAuthDAO();
    public final static GameDAO gameData = new MemoryGameDAO();

    public static UserDAO getUserDAO(){
        return usrData;
    }
    public static AuthDAO getAuthDAO(){
        return authData;
    }
    public static GameDAO getGameDAO(){
        return gameData;
    }

    public static void clear(){
        usrData.clear();
        authData.clear();
        gameData.clear();
    }

//  throws error if not verified
    public static void verifyToken(String authToken) throws DataAccessException{
        authData.getAuthData(authToken);
    }

}
