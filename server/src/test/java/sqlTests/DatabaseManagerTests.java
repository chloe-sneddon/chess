package sqlTests;

import dataaccess.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import service.GeneralService;

public class DatabaseManagerTests {
//    DatabaseManager dbMan = new DatabaseManager();

    @BeforeEach
    public void run() throws Exception {
        GeneralService.clear();
        DatabaseManager.clearTables();
    }

    static private final String[] insertExamples = {
        """
        INSERT INTO authData (username, authToken) VALUES ('Puddles', '123authToken');
        """
    };

    static private final String[] getExamples = {
            """
            Select * from authData;
            """
    };

    @Test
    @DisplayName("Initialize DB")
    public void initializeDB(){

        try (var conn = DatabaseManager.getConnection()){
            DatabaseManager.configureDatabase();
            for (var statement : insertExamples) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception e){
            Assertions.fail("Unable to start server: %s%n " + e.getMessage());
        }
    }
}
