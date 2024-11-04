package dataaccess.auth;

import dataaccess.DataAccessException;
import model.AuthData;

/*
* Data Access Object Interface for Authentication Data (model.AuthData)
*/
public interface AuthDAO {

    void addAuthData(String authToken, String username) throws DataAccessException;
    AuthData getAuthData(String token) throws DataAccessException;
    String getUsername(String token) throws DataAccessException;
    void clear() throws DataAccessException;
    void deleteToken(String authToken) throws DataAccessException;

}
