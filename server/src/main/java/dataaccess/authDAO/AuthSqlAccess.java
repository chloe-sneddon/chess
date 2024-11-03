package dataaccess.authDAO;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.SqlSyntax;
import model.AuthData;
import java.sql.SQLException;

public class AuthSqlAccess implements AuthDAO {

    public void addAuthData(String authToken, String username) throws DataAccessException{
        String addAuthData = SqlSyntax.addAuthData;
        try (var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(addAuthData)) {
                statement.setString(1,username);
                statement.setString(2,authToken);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new DataAccessException("Error: unauthorized", 401);
        }
    }

    public AuthData getAuthData(String token) throws DataAccessException {
        String verifyToken = SqlSyntax.verifyToken;

        try (var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(verifyToken)) {
                statement.setString(1,token);
                var rs = statement.executeQuery();
                rs.next();
                var authToken = rs.getString(1);
                var username = rs.getString(2);
                return new AuthData(authToken,username);
            }
        } catch (Exception e) {
            throw new DataAccessException("Error: unauthorized", 401);
        }

    }

    public String getUsername(String token) throws DataAccessException{
        String getUsername = SqlSyntax.getUsrInAuth;

        try (var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(getUsername)) {
                statement.setString(1,token);
                var rs = statement.executeQuery();
                rs.next();
                return rs.getString(1);
            }
        } catch (Exception e) {
            throw new DataAccessException("Error: unauthorized", 401);
        }
    }

    public void clear() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            String sqlClearDB = "TRUNCATE authData";

            try(var preparedClear = conn.prepareStatement(sqlClearDB)){
                preparedClear.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new DataAccessException(String.format("Unable to get connection: %s", ex.getMessage()),500);
        }
    }

    public void deleteToken(String authToken) throws DataAccessException{
        String deleteToken = SqlSyntax.deleteToken;
        try(var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(deleteToken)) {
                statement.setString(1,authToken);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage(), 500);
        }
    }
}
