package dataaccess;

public class SqlSyntax {
//    AuthData
    static public final String addAuthData = "INSERT INTO authData (username, authToken) VALUES (?, ?);";
    static public final String verifyToken = "SELECT *from authData WHERE binary authToken LIKE ?;";
    static public final String getUsername = "SELECT username from authData WHERE authToken LIKE ?;";
    static public final String deleteToken = "DELETE FROM authData WHERE authToken Like ?;";

//    GameData
    static public final String createGame = "INSERT INTO gameData (gameID, gameName, game) VALUES (?,?,?);";
    static public final String getGameData = "SELECT * from gameData WHERE gameID LIKE ?;";
    static public final String getActiveGames = "SELECT * from gameData;";



//    UserData
    static public final String register = "INSERT INTO userData (username, password, email) VALUES ('Puddles', '123Pass#wordHas#', 'email@email.com');";
    static public final String verifyUsername = "SELECT *from userData where binary username LIKE ?;";
//    static public final String verifyUsername = "SELECT CASE WHEN EXISTS (SELECT * FROM userData WHERE ?) THEN 1 ELSE 0 END AS result;";

}
