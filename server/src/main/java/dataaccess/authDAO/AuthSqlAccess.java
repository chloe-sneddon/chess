package dataaccess.authDAO;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.SqlSyntax;
import model.AuthData;

import java.sql.SQLException;
import dataaccess.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class AuthSqlAccess implements AuthDAO {
    DatabaseManager dbInfo = new DatabaseManager();

    public String createToken(){
        return null;
    }

    public void addAuthData(String authToken, String username){

    }

    public AuthData getAuthData(String token) throws DataAccessException {
        String verifyToken = SqlSyntax.verifyToken;

        try (var conn = DatabaseManager.getConnection()){
            try (var actualToken = conn.prepareStatement(verifyToken)) {
                var rs = actualToken.executeQuery();
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
