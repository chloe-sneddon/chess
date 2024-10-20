package service;

import dataaccess.MemoryUserDOA;
import model.AuthData;
import model.UserData;
import spark.Request;
import spark.Response;


public class UserService extends GeneralService{
//    hashmap
//    public AuthData register(UserData user) {}
//    public AuthData login(UserData user) {}
//    public void logout(AuthData auth) {}
    public static String register(UserData usrData){
        String usrName = usrData.username();
        String pass = usrData.password();
        String email = usrData.email();
//        String tmpReturn = "username: " + usrName + " pass: " + pass + " email: " + email;
        return "username: " + usrName + " pass: " + pass + " email: " + email;
    }
}
