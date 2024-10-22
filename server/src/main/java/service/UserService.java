package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;


public class UserService extends GeneralService{

//  public void logout(AuthData auth) {}
    public static AuthData register(UserData usrData) throws UserServiceException, DataAccessException {

//        TODO: 500 error vs 400 error
            if(usrData == null){
                throw new UserServiceException("usrData is null",500);
            }
            if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
                throw new UserServiceException("Error: bad request",400);
            }
            if (GeneralService.usrData.userExists(usrData.username())){
                throw new UserServiceException("Error: already taken",403);
            }

            GeneralService.usrData.insertUser(usrData);
            String token = authData.createToken();
            authData.addAuthData(token,usrData.username());
            return authData.getAuthData(token);
    }

    public static AuthData login(UserData usrData) throws UserServiceException, DataAccessException{
        if(usrData == null){
            throw new UserServiceException("Error: usrData is null",500);
        }
        if((usrData.username() == null)|(usrData.password() == null)){
            throw new UserServiceException("Error: empty UserData field",500);
        }

//       Verify Username and Password
        if(GeneralService.usrData.userExists(usrData.username())){
            if(usrData.password().equals(GeneralService.usrData.getPassword(usrData.username()))){
                var token = authData.createToken();
                authData.addAuthData(token,usrData.username());
                return authData.getAuthData(token);
            }
            else{
                throw new UserServiceException("Error: unauthorized",401);
            }
        }
        else{
            throw new UserServiceException ("Error: user does not exist",401);
        }
    }

    public static void logout(String authToken) throws DataAccessException {
        verifyToken(authToken);
        authData.deleteToken(authToken);
    }
//    public static boolean verifyPassword(String hashedPassword){}

}
