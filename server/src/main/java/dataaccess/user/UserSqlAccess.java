package dataaccess.user;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.SqlSyntax;
import model.UserData;

import java.sql.SQLException;

public class UserSqlAccess implements UserDAO {

    public String getPassword(String username) throws DataAccessException{
        String getPW = SqlSyntax.GET_PW;
        try(var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(getPW)) {
                statement.setString(1, username);
                var rs = statement.executeQuery();
                rs.next();
                return rs.getString(1);
            }
        }
        catch (Exception e) {
            throw new DataAccessException("Error: bad request",400);
        }
    }

    public void insertUser(UserData u) throws DataAccessException{
        String username = u.username();
        String password = u.password();
        String email = u.password();
        String insertUser = SqlSyntax.INSERT_USER;

        try(var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(insertUser)) {
                statement.setString(1,username);
                statement.setString(2,password);
                statement.setString(3,email);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getLocalizedMessage(),500);
        }
    }

    public boolean userExists(String username){
        String verifyUsername = SqlSyntax.VERIFY_USERNAME;
        try (var conn = DatabaseManager.getConnection()){
            try (var statement = conn.prepareStatement(verifyUsername)) {
                statement.setString(1,username);
                var rs = statement.executeQuery();
                rs.next();
                rs.getString(1);
                return true;
            }
        }
        catch (Exception e) {
            return false;
        }
    }

    public void clear () throws DataAccessException{
        try (var conn = DatabaseManager.getConnection()){
            String sqlClearDB = "TRUNCATE userData";

            try(var preparedClear = conn.prepareStatement(sqlClearDB)){
                preparedClear.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new DataAccessException(String.format("Unable to get connection: %s", ex.getMessage()),500);
        }
    }
}
