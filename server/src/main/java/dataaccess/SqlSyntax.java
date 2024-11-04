package dataaccess;

public class SqlSyntax {
//    AuthData
    static public final String ADD_AUTH_DATA = "INSERT INTO authData (username, authToken) VALUES (?, ?);";
    static public final String VERIFY_TOKEN = "SELECT *from authData WHERE binary authToken LIKE ?;";
    static public final String GET_USR_IN_AUTH = "SELECT username from authData WHERE authToken LIKE ?;";
    static public final String DELETE_TOKEN = "DELETE FROM authData WHERE authToken Like ?;";

//    GameData
    static public final String CREATE_GAME = "INSERT INTO gameData (gameID, gameName, game) VALUES (?,?,?);";
    static public final String GET_GAME_DATA = "SELECT * from gameData WHERE gameID LIKE ?;";
    static public final String GET_ACTIVE_GAMES = "SELECT * from gameData;";
    static public final String UPDATE_WHT_PLAYER = "UPDATE gameData SET whiteUsername = ? WHERE gameID = ?;";
    static public final String UPDATE_BLK_PLAYER = "UPDATE gameData SET blackUsername = ? WHERE gameID = ?;";



//    UserData
    static public final String VERIFY_USERNAME = "SELECT *from userData where binary username LIKE ?;";
    static public final String INSERT_USER = "INSERT INTO userData (username, password, email) VALUES (?, ?, ?);";
    static public final String GET_PW = "SELECT password from userData WHERE username LIKE ?;";

}
