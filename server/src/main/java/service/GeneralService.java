package service;

import dataaccess.*;
import dataaccess.auth.AuthDAO;
import dataaccess.auth.AuthSqlAccess;
import dataaccess.game.GameDAO;
import dataaccess.game.GameSqlAccess;
import dataaccess.user.UserDAO;
import dataaccess.user.UserSqlAccess;

/*
* Performs General Service Duties and stores Interface Implementations for Data Access Objects
*/
public class GeneralService {
    public final static UserDAO USRDATA = new UserSqlAccess();
    public final static AuthDAO AUTHDATA = new AuthSqlAccess();
    public final static GameDAO GAMEDATA = new GameSqlAccess();

    public static UserDAO getUserDAO(){return USRDATA;}

    public static AuthDAO getAuthDAO(){return AUTHDATA;}

    public static GameDAO getGameDAO(){return GAMEDATA;}

    public static void clear() throws DataAccessException{
        USRDATA.clear();
        AUTHDATA.clear();
        GAMEDATA.clear();
    }

//  throws error if not verified
    public static void verifyToken(String authToken) throws DataAccessException{
        AUTHDATA.getAuthData(authToken);
    }

}
