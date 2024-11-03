package dataaccess;

public class SqlSyntax {
//    AuthData
    static public final String addAuthData = "INSERT INTO authData (username, authToken) VALUES (?, ?);";
    static public final String verifyToken = "SELECT *from authData WHERE binary authToken LIKE ?;";
    static public final String getUsrInAuth = "SELECT username from authData WHERE authToken LIKE ?;";
    static public final String deleteToken = "DELETE FROM authData WHERE authToken Like ?;";

//    GameData
    static public final String createGame = "INSERT INTO gameData (gameID, gameName, game) VALUES (?,?,?);";
    static public final String getGameData = "SELECT * from gameData WHERE gameID LIKE ?;";
    static public final String getActiveGames = "SELECT * from gameData;";
    static public final String updateWhtPlayer = "UPDATE gameData SET whiteUsername = ? WHERE gameID = ?;";
    static public final String updateBlkPlayer = "UPDATE gameData SET blackUsername = ? WHERE gameID = ?;";



//    UserData
    static public final String register = "INSERT INTO userData (username, password, email) VALUES ('Puddles', '123Pass#wordHas#', 'email@email.com');";
    static public final String verifyUsername = "SELECT *from userData where binary username LIKE ?;";
    static public final String insertUser = "INSERT INTO userData (username, password, email) VALUES (?, ?, ?);";
    static public final String getPW = "SELECT password from userData WHERE username LIKE ?;";
//    static public final String verifyUsername = "SELECT CASE WHEN EXISTS (SELECT * FROM userData WHERE ?) THEN 1 ELSE 0 END AS result;";

}
