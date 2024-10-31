package service;

import dataaccess.*;
import dataaccess.authDAO.AuthDAO;
import dataaccess.authDAO.AuthSqlAccess;
import dataaccess.authDAO.MemoryAuthDAO;
import dataaccess.gameDAO.GameDAO;
import dataaccess.gameDAO.GameSqlAccess;
import dataaccess.gameDAO.MemoryGameDAO;
import dataaccess.userDAO.MemoryUserDAO;
import dataaccess.userDAO.UserDAO;
import dataaccess.userDAO.UserSqlAccess;

/*
* Performs General Service Duties and stores Interface Implementations for Data Access Objects
*/
public class GeneralService {
    public final static UserDAO USRDATA = new MemoryUserDAO();
    public final static AuthDAO AUTHDATA = new MemoryAuthDAO();
    public final static GameDAO GAMEDATA = new MemoryGameDAO();
    public final static UserDAO USRSQL = new UserSqlAccess();
    public final static AuthDAO AUTHSQL = new AuthSqlAccess();
    public final static GameDAO GAMESQL = new GameSqlAccess();

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
