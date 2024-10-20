package service;

import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDOA;
import dataaccess.MemoryUserDOA;
import model.AuthData;
import model.UserData;
import spark.Request;
import spark.Response;


public class UserService extends GeneralService{

//    public AuthData register(UserData user) {}
//  public AuthData login(UserData user) {}
//  public void logout(AuthData auth) {}
    public static AuthData register(UserData usrData){

        try{
            if(usrData == null){
//                TODO Add error code to ALL EXCEPTIONS [500]
                throw new Exception("usrData is null");
            }
            if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
//                TODO: [400]
                throw new Exception("Empty UserData field");
            }
            if (memUsrData.userExists(usrData.username())){
//                TODO: add error code to exception [403]
                throw new DataAccessException("Error: already taken");
            }

            memUsrData.insertUser(usrData);
//          create token and add to authData
            String token = memAuthData.createToken();
            memAuthData.addAuthData(token,usrData.username());

            return memAuthData.getAuthData(usrData.username());
        }

        catch(Exception e){
            return null;
        }
    }
}
