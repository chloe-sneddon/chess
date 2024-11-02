package dataaccess.userDAO;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import dataaccess.SqlSyntax;
import model.AuthData;
import model.UserData;

import java.sql.SQLException;

public class UserSqlAccess implements UserDAO {
    public String getPassword(String username) throws DataAccessException{return null;}

    public void insertUser(UserData u) throws DataAccessException{
        String username = u.username();
        String email = u.email();
        String password = u.password();
    }
    public boolean userExists(String username){
        String verifyUsername = SqlSyntax.verifyUsername;
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
