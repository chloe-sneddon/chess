package dataaccess;

import model.UserData;

import java.util.HashMap;

public class MemoryUserDOA implements UserDOA{
    private HashMap<String, UserData> allUserData = new HashMap <String, UserData>();

    public void insertUser(UserData u) throws DataAccessException{
//        if UserData is null throw an exception
        if(u == null){
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
