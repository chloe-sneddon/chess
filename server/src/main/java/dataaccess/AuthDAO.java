package dataaccess;

import model.AuthData;

//createAuth: Create a new authorization.
//getAuth: Retrieve an authorization given an authToken.
//deleteAuth: Delete an authorization so that it is no longer valid.
public interface AuthDAO {
    public String createToken();
    public void addAuthData(String authToken, String username);
    public String authString(String username);
    public AuthData getAuthData(String username);
    public void clear();

}
