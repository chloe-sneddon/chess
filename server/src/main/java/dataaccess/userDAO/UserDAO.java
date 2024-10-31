package dataaccess.userDAO;

import dataaccess.DataAccessException;
import model.UserData;

/*
 * Data Access Object Interface for User Data (model.UserData)
 */
public interface UserDAO {

//    public UserData getUserData(String usrname) throws DataAccessException;
    public String getPassword(String username) throws DataAccessException;
//    public String getEmail(String username) throws DataAccessException;
    public void insertUser(UserData u) throws DataAccessException;
    public boolean userExists(String username);
    public void clear ();

}
