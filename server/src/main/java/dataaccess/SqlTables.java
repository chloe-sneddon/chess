package dataaccess;

import java.util.ArrayList;

public class SqlTables {
//    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
    static private final String[] CREATE_USER_TABLE = {
            """
        CREATE TABLE IF NOT EXISTS userData (
        `username` VARCHAR(256) NOT NULL,
        `password` VARCHAR(500) NOT NULL,
        `email` VARCHAR(300) NOT NULL,
        PRIMARY KEY (`username`),
        INDEX(email),
        INDEX(password)
        )
        """
    };
    static private final String[] CREATE_AUTH_TABLE = {
        """
        CREATE TABLE IF NOT EXISTS authData (
        `authToken` VARCHAR(300) NOT NULL,
        `username` VARCHAR(300) NOT NULL,
        PRIMARY KEY (`authToken`),
        INDEX(`authToken`),
        INDEX(username)
        )
        """
    };

//`json` TEXT DEFAULT NULL,
    static private final String[] CREATE_GAME_TABLE = {
            """
        CREATE TABLE IF NOT EXISTS gameData (
        `gameID` INT NOT NULL,
        `blackUsername` VARCHAR(300) DEFAULT NULL,
        `whiteUsername` VARCHAR(300) DEFAULT NULL,
        `gameName` VARCHAR(300) NOT NULL,
        `game` TEXT NOT NULL,
        INDEX(gameID),
        INDEX(gameName)
        )
        """
    };

    public static ArrayList<String[]> allTables(){
        ArrayList <String[]> tables = new ArrayList<>();
        tables.add(CREATE_USER_TABLE);
        tables.add(CREATE_AUTH_TABLE);
        tables.add(CREATE_GAME_TABLE);
        return tables;
    }
}
