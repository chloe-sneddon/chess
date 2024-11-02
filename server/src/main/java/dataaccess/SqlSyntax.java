package dataaccess;

public class SqlSyntax {
//    AuthData
    static private final String insertToAuthData = "INSERT INTO authData (username, authToken) VALUES (?, ?);";
    static private final String selectAllAuthData = "SELECT * from authData;";
    static private final String getUsernames = "SELECT username from authData;";
//    GameData
    static private final String insertToGameData = "INSERT INTO gameData (?, ?) VALUES (?, ?);";
    static private final String selectAllGameData = "SELECT * from gameData;";
    static private final String getWhiteUsernames = "SELECT whiteUsername from gameData;";
//    UserData

}
