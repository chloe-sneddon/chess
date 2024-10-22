package dataaccess;

import model.UserData;
//clear: A method for clearing all data from the database. This is used during testing.
//createUser: Create a new user.
//getUser: Retrieve a user with the given username.

public interface UserDAO {
    public UserData getUserData(String usrname) throws DataAccessException;
    public String getPassword(String username) throws DataAccessException;
    public String getEmail(String username) throws DataAccessException;
    public void insertUser(UserData u) throws DataAccessException;
    public boolean userExists(String username);
    public void clear ();
}
