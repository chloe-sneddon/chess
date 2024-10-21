package dataaccess;

import model.UserData;

import java.util.HashMap;

public class MemoryUserDAO implements UserDAO {
    private HashMap<String, UserData> allUserData = new HashMap <String, UserData>();

    public String getUsername(UserData u){
        return u.username();
    }
    public UserData getUser(String usrname){
        return allUserData.get(usrname);
    }

    public String getPassword(String username){
//        TODO: Encode this step (get hash of password or something)
        UserData u = allUserData.get(username);
        return u.password();
    }

    public String getEmail(String username){
        UserData u = allUserData.get(username);
        return u.email();
    }

    public void insertUser(UserData u) throws DataAccessException{
//        if UserData is null throw an exception
        if(u == null){
//           TODO: [400]
            throw new DataAccessException("No UserData to insert");
        }
        allUserData.put(u.username(),u);
    }

    public boolean userExists(String username){
        boolean exists = false;
        if (allUserData.get(username) != null){
            exists = true;
        }
        return exists;
    }

}
