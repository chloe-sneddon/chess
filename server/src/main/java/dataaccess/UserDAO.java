package dataaccess;

import model.UserData;
//clear: A method for clearing all data from the database. This is used during testing.
//createUser: Create a new user.
//getUser: Retrieve a user with the given username.

public interface UserDAO {
    public UserData getUser(String usrname);
    public String getPassword(String username);
    public String getEmail(String username);
    public void insertUser(UserData u) throws DataAccessException;
    public boolean userExists(String username);
    public void clear ();
}
