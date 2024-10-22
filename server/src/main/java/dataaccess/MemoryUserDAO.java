package dataaccess;

import model.UserData;
import java.util.HashMap;

public class MemoryUserDAO implements UserDAO {
    private HashMap<String, UserData> allUserData = new HashMap <String, UserData>();

    public String getPassword(String username) throws DataAccessException{
//        TODO: Phase 4, Encode this step (get hash of password or something)
        if(allUserData.isEmpty()){
            throw new DataAccessException("Error: bad request",400);
        }

        UserData u = allUserData.get(username);
        return u.password();
    }

    public void insertUser(UserData u){
        allUserData.put(u.username(),u);
    }

    public boolean userExists(String username){
        boolean exists = false;
        if (allUserData.get(username) != null){
            exists = true;
        }
        return exists;
    }

    public void clear(){ allUserData.clear(); }

}
