package dataaccess;

import model.AuthData;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {
    private HashMap<String, AuthData> allAuthData = new HashMap <String, AuthData>();

    public String createToken(){
        return "this is a token";
    }

    public void addAuthData(String authToken, String username){
        AuthData authData = new AuthData(authToken, username);
        allAuthData.put(username, authData);
    }

    public String authString(String username){
//        { "username":"", "authToken":"" }
        AuthData authData = allAuthData.get(username);
        return authData.toString();
    }

    public AuthData getAuthData(String username){
        return allAuthData.get(username);
    }

    public void clear() {
        allAuthData.clear();
    }
}
