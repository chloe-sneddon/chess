package dataaccess;

import model.AuthData;
import java.util.UUID;

//createAuth: Create a new authorization.
//getAuth: Retrieve an authorization given an authToken.
//deleteAuth: Delete an authorization so that it is no longer valid.
interface AuthDOA {

    public void deleteAuth(AuthData token);
    public int getAuth(String username);
//    int getAuth()
}
