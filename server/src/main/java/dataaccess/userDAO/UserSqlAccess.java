package dataaccess.userDAO;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import model.UserData;

import java.sql.SQLException;

public class UserSqlAccess implements UserDAO {
    public String getPassword(String username) throws DataAccessException{return null;}
    public void insertUser(UserData u) throws DataAccessException{}
    public boolean userExists(String username){return false;}

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
