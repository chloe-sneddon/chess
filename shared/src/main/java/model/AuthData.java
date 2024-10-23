package model;
//authToken	String
//username	String
public record AuthData(String authToken, String username) {
    @Override
    public String toString() {
        return "authToken:" + authToken + ",username:" + username;

    }
}
