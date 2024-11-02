package dataaccess;

public class SqlSyntax {
//    AuthData
    static public final String addAuthToken = "INSERT INTO authData (username, authToken) VALUES (?, ?);";
    static public final String verifyToken = "select *from authData where binary authToken LIKE ?;";
    static public final String selectAllAuthData = "SELECT * from authData;";
    static public final String getUsernames = "SELECT username from authData;";

//    GameData
    static public final String insertToGameData = "INSERT INTO gameData (?, ?) VALUES (?, ?);";
    static public final String selectAllGameData = "SELECT * from gameData;";
    static public final String getWhiteUsernames = "SELECT whiteUsername from gameData;";

//    UserData
    static public final String register = "INSERT INTO userData (username, password, email) VALUES ('Puddles', '123Pass#wordHas#', 'email@email.com');";

}
