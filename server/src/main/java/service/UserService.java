package service;

import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;


public class UserService extends GeneralService{

//  public AuthData login(UserData user) {}
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
            if (UsrData.userExists(usrData.username())){
//                TODO: add error code to exception [403]
                throw new DataAccessException("Error: already taken");
            }

            UsrData.insertUser(usrData);
//          create token and add to authData
            String token = memAuthData.createToken();
            memAuthData.addAuthData(token,usrData.username());
//          TODO: Status Code Success [200]
            return memAuthData.getAuthData(usrData.username());
    }

    public static AuthData login(UserData usrData) throws Exception{
        if(usrData == null){
//                TODO Add error status code [500]
            throw new Exception("usrData is null");
        }
        if((usrData.username() == null)|(usrData.password() == null)){
//                TODO: [400]
            throw new Exception("Empty UserData field");
        }
//       Verify Username and Password
        if(UsrData.userExists(usrData.username())){
            if(usrData.password().equals(UsrData.getPassword(usrData.username()))){
//                happy route
//                TODO: Status Code Success [200]
                var token = memAuthData.createToken();
                memAuthData.addAuthData(token,usrData.username());
                return memAuthData.getAuthData(usrData.username());
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
}
