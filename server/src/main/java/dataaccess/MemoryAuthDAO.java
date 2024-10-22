package dataaccess;

import model.AuthData;

import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {
    private HashMap<String, AuthData> allAuthData = new HashMap <String, AuthData>();

    public String createToken(){
//        return "this is a token";
        return UUID.randomUUID().toString();
    }

    public void addAuthData(String authToken, String username){
        AuthData authData = new AuthData(authToken, username);
        allAuthData.put(authToken, authData);
    }

    public AuthData getAuthData(String token) throws DataAccessException{
        if(allAuthData.get(token) == null){
            throw new DataAccessException("Error: unauthorized", 401);
        }
        return allAuthData.get(token);
    }

    public void clear() {
        allAuthData.clear();
    }

    public void deleteToken(String authToken){
        allAuthData.remove(authToken);
    }

}
