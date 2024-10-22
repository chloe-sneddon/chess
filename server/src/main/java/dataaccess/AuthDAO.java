package dataaccess;

import model.AuthData;

/*
* Data Access Object Interface for Authentication Data (model.AuthData)
*/
public interface AuthDAO {

    String createToken();
    void addAuthData(String authToken, String username);
    AuthData getAuthData(String token) throws DataAccessException;
    String getUsername(String token) throws DataAccessException;
    void clear();
    void deleteToken(String authToken);

}
