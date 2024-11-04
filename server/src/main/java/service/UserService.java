package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

/*
 * Performs User Service Duties
 */
public class UserService extends GeneralService{

    public static AuthData register(UserData usrData) throws ServiceException, DataAccessException {

        if(usrData == null){
            throw new ServiceException("usrData is null",500);
        }
        if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
            throw new ServiceException("Error: bad request",400);
        }
        if (USRDATA.userExists(usrData.username())){
            throw new ServiceException("Error: already taken",403);
        }
        String pw = hashPW(usrData.password());
        UserData storedData = new UserData(usrData.username(), pw,usrData.email());

        GeneralService.USRDATA.insertUser(storedData);
        String token = UUID.randomUUID().toString();
        AUTHDATA.addAuthData(token,usrData.username());
        return AUTHDATA.getAuthData(token);
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
            if(!(BCrypt.checkpw(password, GeneralService.USRDATA.getPassword(username)))){
                throw new ServiceException("Error: unauthorized",401);
            }
        }
        else{
            throw new ServiceException("Error: user does not exist",401);
        }
    }

    private static String hashPW(String text){
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }
//    boolean verifyUser(String username, String providedClearTextPassword) {
//        // read the previously hashed password from the database
//        var hashedPassword = readHashedPasswordFromDatabase(username);
//
//        return BCrypt.checkpw(providedClearTextPassword, hashedPassword);
//    }

}
