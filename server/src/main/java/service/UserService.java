package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;


public class UserService extends GeneralService{

//  public void logout(AuthData auth) {}
    public static AuthData register(UserData usrData) throws Exception{

            if(usrData == null){
//                TODO Add error code to ALL EXCEPTIONS [500]
                throw new Exception("usrData is null");
            }
            if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
//                TODO: [400]
                throw new Exception("Empty UserData field");
            }
            if (GeneralService.usrData.userExists(usrData.username())){
//                TODO: add error code to exception [403]
                throw new DataAccessException("Error: already taken");
            }

            GeneralService.usrData.insertUser(usrData);
//          create token and add to authData
            String token = authData.createToken();
            authData.addAuthData(token,usrData.username());
            var returnVar = authData.getAuthData(token);
//          TODO: Status Code Success [200]
            return returnVar;
    }

    public static AuthData login(UserData usrData) throws Exception{
        if(usrData == null){
//                TODO: Add error status code [500]
            throw new Exception("usrData is null");
        }
        if((usrData.username() == null)|(usrData.password() == null)){
//                TODO: [400]
            throw new Exception("Empty UserData field");
        }
//       Verify Username and Password
        if(GeneralService.usrData.userExists(usrData.username())){
            if(usrData.password().equals(GeneralService.usrData.getPassword(usrData.username()))){
//                TODO: Status Code Success [200]
                var token = authData.createToken();
                authData.addAuthData(token,usrData.username());
                return authData.getAuthData(token);
            }
            else{
//              TODO: [401] - unauthorized
                throw new Exception("Unauthorized");
            }
        }
        else{
//            TODO: [500]
            throw new Exception ("User does not exist");
        }
    }

    public static void logout(String authToken) throws Exception {
        verifyToken(authToken);
        authData.deleteToken(authToken);
    }
}
