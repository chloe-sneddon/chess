package sqlTests;

import dataaccess.DataAccessException;
import dataaccess.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import service.GeneralService;

import java.sql.Connection;

public class DatabaseManagerTests {

    @BeforeEach
    public void run() throws Exception {
        GeneralService.clear();
    }

    static private final String [] insertExamples = {
        """
        INSERT INTO authData (username, authToken) VALUES ('Puddles', '123authToken');
        """
    };

    static private final String getToken = "Select * from authData;";

    static private final String getUsername = "Select username from authData;";

    @Test
    @DisplayName("Initialize DB")
    public void initializeDB(){
        try{
            DatabaseManager.configureDatabase();
        }
        catch(DataAccessException e){
            Assertions.fail(e.message());
        }
    }

    @Test
    @DisplayName("Clear db")
    public void clearDB(){
        try {
            DatabaseManager.configureDatabase();
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
        try (var conn = DatabaseManager.getConnection()){

            for (var statement : insertExamples) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
            checkData(conn);
        }
        catch(Exception e){
            Assertions.fail("DB not initialized: %s%n " + e.getMessage());
        }
        try (var conn = DatabaseManager.getConnection()){
            GeneralService.clear();
            checkData(conn);
            Assertions.fail();
        }
        catch(Exception e){
            System.out.println("success");
        }
    }

    private void checkData(Connection conn) throws Exception{
        String actual;
        try (var actualUsername = conn.prepareStatement(getToken)) {
            var rs = actualUsername.executeQuery();
            rs.next();
            actual = rs.getString(1);
            String expectedToken = "123authToken";

            if(!(expectedToken.equals(actual))){
                throw new Exception("authToken not equal");
            }
        }
        try (var actualUsername = conn.prepareStatement(getUsername)) {
            var rs = actualUsername.executeQuery();
            rs.next();
            actual = rs.getString(1);
            String expectedUser = "Puddles";

            if(!(expectedUser.equals(actual))){
                throw new Exception("authToken not equal");
            }
        }
    }
}
