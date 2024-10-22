package dataaccess;

import model.AuthData;

//createAuth: Create a new authorization.
//getAuth: Retrieve an authorization given an authToken.
//deleteAuth: Delete an authorization so that it is no longer valid.
public interface AuthDAO {
    String createToken();
    void addAuthData(String authToken, String username);
    AuthData getAuthData(String token) throws DataAccessException;
    String getUsername(String token) throws DataAccessException;
    void clear();
    void deleteToken(String authToken);

}
