package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;

/*
 * Performs User Service Duties
 */
public class UserService extends GeneralService{

    public static AuthData register(UserData usrData) throws ServiceException, DataAccessException {

//        TODO: 500 error vs 400 error
            if(usrData == null){
                throw new ServiceException("usrData is null",500);
            }
            if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
                throw new ServiceException("Error: bad request",400);
            }
            if (GeneralService.USRDATA.userExists(usrData.username())){
                throw new ServiceException("Error: already taken",403);
            }

            GeneralService.USRDATA.insertUser(usrData);
            String token = AUTHDATA.createToken();
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
        var token = AUTHDATA.createToken();
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
