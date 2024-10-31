package dataaccess.userDAO;

import dataaccess.DataAccessException;
import model.UserData;

public class UserSqlAccess implements UserDAO {
    public String getPassword(String username) throws DataAccessException{return null;}
    public void insertUser(UserData u) throws DataAccessException{}
    public boolean userExists(String username){return false;}
    public void clear (){}
}
