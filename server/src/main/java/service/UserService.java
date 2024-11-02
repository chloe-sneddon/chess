package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;

import java.util.UUID;

/*
 * Performs User Service Duties
 */
public class UserService extends GeneralService{
//    public final static UserDAO USRSQL = new UserSqlAccess();
//    public final static AuthDAO AUTHSQL = new AuthSqlAccess();
//    public final static GameDAO GAMESQL = new GameSqlAccess();

    public static AuthData register(UserData usrData) throws ServiceException, DataAccessException {

        if(usrData == null){
            throw new ServiceException("usrData is null",500);
        }
        if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
            throw new ServiceException("Error: bad request",400);
        }
        if (USRSQL.userExists(usrData.username())){
            throw new ServiceException("Error: already taken",403);
        }
//      String hash = USRSQL.hashPW(String pw);
//      UserData storedData = new UserData(username,hash,email);
//      USRSQL.insertUser(storedData); // delete the line below
        USRSQL.insertUser(usrData);
        String token = UUID.randomUUID().toString();
        AUTHSQL.addAuthData(token,usrData.username());
        return AUTHSQL.getAuthData(token);

//        GeneralService.USRDATA.insertUser(usrData);
//        String token2 = AUTHDATA.createToken();
//        AUTHDATA.addAuthData(token2,usrData.username());
//        return AUTHDATA.getAuthData(token2);
//        if (GeneralService.USRDATA.userExists(usrData.username())){
//            throw new ServiceException("Error: already taken",403);
//        }
    }

    public static AuthData login(UserData usrData) throws ServiceException, DataAccessException{
        if(usrData == null){
            throw new ServiceException("Error: usrData is null",500);
        }
        if((usrData.username() == null)|(usrData.password() == null)){
            throw new ServiceException("Error: empty UserData field",500);
        }

        verifyPassword(usrData.username(),usrData.password());
        var token = UUID.randomUUID().toString();
        AUTHDATA.addAuthData(token,usrData.username());
        return AUTHDATA.getAuthData(token);

    }

    public static void logout(String authToken) throws DataAccessException {
        verifyToken(authToken);
        AUTHDATA.deleteToken(authToken);
    }

    public static void verifyPassword(String username, String password) throws ServiceException,DataAccessException{

        if(GeneralService.USRDATA.userExists(username)){
            if(!(password.equals(GeneralService.USRDATA.getPassword(username)))){
                throw new ServiceException("Error: unauthorized",401);
            }
        }
        else{
            throw new ServiceException("Error: user does not exist",401);
        }
    }


}
