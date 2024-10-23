package service;

import dataaccess.*;

/*
* Performs General Service Duties and stores Interface Implementations for Data Access Objects
*/
public class GeneralService {
    public final static UserDAO USRDATA = new MemoryUserDAO();
    public final static AuthDAO AUTHDATA = new MemoryAuthDAO();
    public final static GameDAO GAMEDATA = new MemoryGameDAO();

    public static UserDAO getUserDAO(){return USRDATA;}

    public static AuthDAO getAuthDAO(){return AUTHDATA;}

    public static GameDAO getGameDAO(){return GAMEDATA;}

    public static void clear(){
        USRDATA.clear();
        AUTHDATA.clear();
        GAMEDATA.clear();
    }

//  throws error if not verified
    public static void verifyToken(String authToken) throws DataAccessException{
        AUTHDATA.getAuthData(authToken);
    }

}
