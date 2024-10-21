package model;
//authToken	String
//username	String
public record AuthData(String authToken, String username) {
//    setAuthToken(int newToken){
//      return new AuthData(newToken, username);
//    }
//    deleteAuth(String token){
//      return new(0, username)
//    }

    @Override
    public String toString() {
        return "authToken:" + authToken + ",username:" + username;

    }
}
