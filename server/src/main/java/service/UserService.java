package service;

import dataaccess.DataAccessException;
import dataaccess.MemoryUserDOA;
import model.AuthData;
import model.UserData;
import spark.Request;
import spark.Response;


public class UserService extends GeneralService{
//    todo: if i have problems here then it could be the static usage
    private final static MemoryUserDOA memUsrData = new MemoryUserDOA();
//    public AuthData register(UserData user) {}
//    public AuthData login(UserData user) {}
//    public void logout(AuthData auth) {}
    public static String register(UserData usrData){

        try{
            if(usrData == null){
//                TODO Add error code to ALL EXCEPTIONS [500]
                throw new Exception("usrData is null");
            }
            if((usrData.username() == null)|(usrData.password() == null)|(usrData.email() == null)){
//                [500]
                throw new Exception("Empty Data field");
            }
            if (memUsrData.userExists(usrData.username())){
//                TODO: add error code to exception [400]
                throw new DataAccessException("Error: already taken");
            }
            memUsrData.insertUser(usrData);

//            return "username: " + usrName + " pass: " + pass + " email: " + email;
            return "hello";
        }
        catch(Exception e){
            return e.toString();
        }

//        String tmpReturn = "username: " + usrName + " pass: " + pass + " email: " + email;
    }
}
