package dataaccess.authDAO;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.SqlSyntax;
import model.AuthData;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthSqlAccess implements AuthDAO {

    public String createToken(){
        return null;
    }

    public void addAuthData(String authToken, String username){

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
                AuthData allData = new AuthData(authToken,username);
                return allData;
            }
        } catch (Exception e) {
            throw new DataAccessException("Error: unauthorized", 401);
        }

    }

    public String getUsername(String token) throws DataAccessException{
        return null;
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

    public void deleteToken(String authToken){

    }
}
