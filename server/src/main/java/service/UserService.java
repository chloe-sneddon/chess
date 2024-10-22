package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;


public class UserService extends GeneralService{

//  public void logout(AuthData auth) {}
    public static AuthData register(UserData usrData) throws ServiceException, DataAccessException {

//        TODO: 500 error vs 400 error
            if(usrData == null){
                throw new ServiceException("usrData is null",500);
            }
            if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
                throw new ServiceException("Error: bad request",400);
            }
            if (GeneralService.usrData.userExists(usrData.username())){
                throw new ServiceException("Error: already taken",403);
            }

            GeneralService.usrData.insertUser(usrData);
            String token = authData.createToken();
            authData.addAuthData(token,usrData.username());
            return authData.getAuthData(token);
    }

    public static AuthData login(UserData usrData) throws ServiceException, DataAccessException{
        if(usrData == null){
            throw new ServiceException("Error: usrData is null",500);
        }
        if((usrData.username() == null)|(usrData.password() == null)){
            throw new ServiceException("Error: empty UserData field",500);
        }

//       Verify Username and Password
        if(GeneralService.usrData.userExists(usrData.username())){
            if(usrData.password().equals(GeneralService.usrData.getPassword(usrData.username()))){
                var token = authData.createToken();
                authData.addAuthData(token,usrData.username());
                return authData.getAuthData(token);
            }
            else{
                throw new ServiceException("Error: unauthorized",401);
            }
        }
        else{
            throw new ServiceException("Error: user does not exist",401);
        }
    }

    public static void logout(String authToken) throws DataAccessException {
        verifyToken(authToken);
        authData.deleteToken(authToken);
    }
//    public static boolean verifyPassword(String hashedPassword){}

}
