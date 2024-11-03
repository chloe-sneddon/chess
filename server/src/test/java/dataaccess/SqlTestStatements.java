package dataaccess;

import java.util.ArrayList;

public class SqlTestStatements {
    static private final String insertAuthExamples = "INSERT INTO authData (username, authToken) VALUES ('Puddles', '123authToken');";

    static private final String getToken = "Select authToken from authData;";
    static private final String expectedToken = "123authToken";

    static private final String getAuthUsername = "Select username from authData;";
    static private final String expectedAuthUsername = "Puddles";

    //    gameData
    static private final String insertGameExamples = "INSERT INTO gameData (gameID, blackUsername, whiteUsername, gameName, game) " +
            "VALUES (123, 'black', 'white','named_game','{\"board\":{\"board\":[[{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}," +
            "{\"pieceColor\":\"WHITE\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",\"pieceType\":" +
            "\"QUEEN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"KING\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\"," +
            "\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}],[{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":" +
            "\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\"" +
            ":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":" +
            "\"WHITE\",\"pieceType\":\"PAWN\"}],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null," +
            "null],[null,null,null,null,null,null,null,null],[{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}]," +
            "[{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"QUEEN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"KING\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"}]]},\"teamTurn\":\"WHITE\"}\n');";

    static public final String insertGameTemp = "INSERT INTO gameData (gameID, gameName, game) " +
            "VALUES (?, ?,'{\"board\":{\"board\":[[{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}," +
            "{\"pieceColor\":\"WHITE\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",\"pieceType\":" +
            "\"QUEEN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"KING\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\"," +
            "\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}],[{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":" +
            "\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\"" +
            ":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":" +
            "\"WHITE\",\"pieceType\":\"PAWN\"}],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null," +
            "null],[null,null,null,null,null,null,null,null],[{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}]," +
            "[{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"QUEEN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"KING\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"}]]},\"teamTurn\":\"WHITE\"}\n');";

    static public final String jsonBoard =
            "'{\"board\":{\"board\":[[{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}," +
            "{\"pieceColor\":\"WHITE\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",\"pieceType\":" +
            "\"QUEEN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"KING\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\"," +
            "\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}],[{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":" +
            "\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\"" +
            ":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":" +
            "\"WHITE\",\"pieceType\":\"PAWN\"}],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null," +
            "null],[null,null,null,null,null,null,null,null],[{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}]," +
            "[{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"QUEEN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"KING\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"}," +
            "{\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"}]]},\"teamTurn\":\"WHITE\"}\n');";

    static private final String getGameID = "Select gameID from gameData;";
    static public final String expectedGameID = "123";

    static private final String getBlackUsername = "Select blackUsername from gameData;";
    static private final String expectedBlackUsername = "black";

    static private final String getWhiteUsername = "Select whiteUsername from gameData;";
    static private final String expectedWhiteUsername = "white";

    static private final String getGameName = "Select gameName from gameData;";
    static private final String expectedGameName = "named_game";

    static private final String getGame = "Select game from gameData;";
//    static private final String expectedGame = "gameButSerialized";


    //    userData
    static private final String insertUserExamples = "INSERT INTO userData (username, password, email) VALUES ('Puddles', '123Pass#wordHas#', 'email@email.com');";

    static private final String getUserUsername = "Select username from userData;";
    static private final String expectedUserUsername = "Puddles";

    static private final String getHashedPW = "Select password from userData;";
    static private final String expectedHashedPW = "123Pass#wordHas#";

    static private final String getEmail = "Select email from userData;";
    static private final String expectedEmail = "email@email.com";


    public static ArrayList<String[]> getSelectsAndExpected (){
        ArrayList<String[]> tmp = new ArrayList<>();
        String[] authToken = {getToken,expectedToken};
        String[]  authUser = {getAuthUsername,expectedAuthUsername};
        String[]  gameID = {getGameID,expectedGameID};
        String[]  blackUser = {getBlackUsername,expectedBlackUsername};
        String[]  whiteUser = {getWhiteUsername,expectedWhiteUsername};
        String[]  gameName = {getGameName,expectedGameName};
        String[]  userUsername = {getUserUsername,expectedUserUsername};
        String[]  hashedPW = {getHashedPW,expectedHashedPW};
        String[]  email = {getEmail,expectedEmail};

        tmp.add(authToken);
        tmp.add(authUser);
        tmp.add(gameID);
        tmp.add(blackUser);
        tmp.add(whiteUser);
        tmp.add(gameName);
        tmp.add(userUsername);
        tmp.add(hashedPW);
        tmp.add(email);

        return tmp;
    }

    public static ArrayList<String> getAddExamples(){
        ArrayList <String> tmp = new ArrayList<>();
        tmp.add(insertAuthExamples);
        tmp.add(insertGameExamples);
        tmp.add(insertUserExamples);
        return tmp;
    }
}
