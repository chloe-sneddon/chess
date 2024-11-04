package dataaccess;

import java.util.ArrayList;

public class SqlTestStatements {
    static private final String INSERT_AUTH_EXAMPLES = "INSERT INTO authData (username, authToken) VALUES ('Puddles', '123authToken');";

    static private final String GET_TOKEN = "Select authToken from authData;";
    static private final String EXPECTED_TOKEN = "123authToken";

    static private final String GET_AUTH_USERNAME = "Select username from authData;";
    static private final String EXPECTED_AUTH_USERNAME = "Puddles";

    //    gameData

    static private final String INSERT_GAME_EXAMPLES = """
            INSERT INTO gameData (gameID, blackUsername, whiteUsername, gameName, game)
            VALUES (123, 'black', 'white','named_game','{\"board\":{\"board\":[[{\"pieceColor\":\"WHITE\",
            \"pieceType\":\"ROOK\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",
            \"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",\"pieceType\":
            \"QUEEN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"KING\"},{\"pieceColor\":
            \"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",
            \"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}],
            [{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":
            \"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},
            {\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\"
            :\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},
            {\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":
            \"WHITE\",\"pieceType\":\"PAWN\"}],[null,null,null,null,null,null,null,null],
            [null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,
            null],[null,null,null,null,null,null,null,null],[{\"pieceColor\":\"BLACK\",\"
            pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},
            {\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"
            pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},
            {\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"
            pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}],
            [{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"},{\"pieceColor\":\"BLACK\",\"
            pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"},
            {\"pieceColor\":\"BLACK\",\"pieceType\":\"QUEEN\"},{\"pieceColor\":\"BLACK\",\"
            pieceType\":\"KING\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"},
            {\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"
            pieceType\":\"ROOK\"}]]},\"teamTurn\":\"WHITE\"}\n');
            """;

static public final String INSERT_GAME_TEMP = """
        INSERT INTO gameData (gameID, gameName, game)
        VALUES (?, ?,'{\"board\":{\"board\":[[{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"},
        {\"pieceColor\":\"WHITE\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\"
        ,\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",\"pieceType\":
        \"QUEEN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"KING\"},{\"pieceColor
        \":\"WHITE\",\"pieceType\":\"BISHOP\"},{\"pieceColor\":\"WHITE\",
        \"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"ROOK\"}
        ],[{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":
        \"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\
        "PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\"
        :\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"
        PAWN\"},{\"pieceColor\":\"WHITE\",\"pieceType\":\"PAWN\"},{\"pieceColor\":
        \"WHITE\",\"pieceType\":\"PAWN\"}],[null,null,null,null,null,null,null,null],
        [null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,
        null],[null,null,null,null,null,null,null,null],[{\"pieceColor\":\"BLACK\",\"
        pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},
        {\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"
        pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},
        {\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"
        pieceType\":\"PAWN\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"PAWN\"}],
        [{\"pieceColor\":\"BLACK\",\"pieceType\":\"ROOK\"},{\"pieceColor\":\"BLACK\",\"
        pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"},
        {\"pieceColor\":\"BLACK\",\"pieceType\":\"QUEEN\"},{\"pieceColor\":\"BLACK\",\"
        pieceType\":\"KING\"},{\"pieceColor\":\"BLACK\",\"pieceType\":\"BISHOP\"},
        {\"pieceColor\":\"BLACK\",\"pieceType\":\"KNIGHT\"},{\"pieceColor\":\"BLACK\",\"
        pieceType\":\"ROOK\"}]]},\"teamTurn\":\"WHITE\"}\n');
        """;

    static private final String GET_GAME_ID = "Select gameID from gameData;";
    static public final String EXPECTED_GAME_ID = "123";

    static private final String GET_BLACK_USERNAME = "Select blackUsername from gameData;";
    static private final String EXPECTED_BLACK_USERNAME = "black";

    static private final String GET_WHITE_USERNAME = "Select whiteUsername from gameData;";
    static private final String EXPECTED_WHITE_USERNAME = "white";

    static private final String GET_GAME_NAME = "Select gameName from gameData;";
    static private final String EXPECTED_GAME_NAME = "named_game";

    //    userData
    static private final String INSERT_USER_EXAMPLES = "INSERT INTO userData (username, password, email) VALUES "
            +"('Puddles', '123Pass#wordHas#', 'email@email.com');";

    static private final String GET_USER_USERNAME = "Select username from userData;";
    static private final String EXPECTED_USER_USERNAME = "Puddles";


    static private final String GET_EMAIL = "Select email from userData;";
    static private final String EXPECTED_EMAIL = "email@email.com";


    public static ArrayList<String[]> getSelectsAndExpected (){
        ArrayList<String[]> tmp = new ArrayList<>();
        String[] authToken = {GET_TOKEN, EXPECTED_TOKEN};
        String[]  authUser = {GET_AUTH_USERNAME, EXPECTED_AUTH_USERNAME};
        String[]  gameID = {GET_GAME_ID, EXPECTED_GAME_ID};
        String[]  blackUser = {GET_BLACK_USERNAME, EXPECTED_BLACK_USERNAME};
        String[]  whiteUser = {GET_WHITE_USERNAME, EXPECTED_WHITE_USERNAME};
        String[]  gameName = {GET_GAME_NAME, EXPECTED_GAME_NAME};
        String[]  userUsername = {GET_USER_USERNAME, EXPECTED_USER_USERNAME};
        String[]  email = {GET_EMAIL, EXPECTED_EMAIL};

        tmp.add(authToken);
        tmp.add(authUser);
        tmp.add(gameID);
        tmp.add(blackUser);
        tmp.add(whiteUser);
        tmp.add(gameName);
        tmp.add(userUsername);
        tmp.add(email);
        return tmp;
    }

    public static ArrayList<String> getAddExamples(){
        ArrayList <String> tmp = new ArrayList<>();
        tmp.add(INSERT_AUTH_EXAMPLES);
        tmp.add(INSERT_GAME_EXAMPLES);
        tmp.add(INSERT_USER_EXAMPLES);
        return tmp;
    }
}
