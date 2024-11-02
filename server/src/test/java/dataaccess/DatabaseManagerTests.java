package dataaccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import service.GeneralService;
import java.sql.Connection;
import java.util.ArrayList;

public class DatabaseManagerTests {

    @BeforeEach
    public void run() throws Exception {
        GeneralService.clear();
        DatabaseManager.configureDatabase();
    }
    
    @Test
    @DisplayName("Initialize DB")
    public void initializeDB(){

        try (var conn = DatabaseManager.getConnection()){
            GeneralService.clear();
            DatabaseManager.configureDatabase();
            addData(conn);
            checkData(conn);
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Clear db")
    public void clearDB(){
        try (var conn = DatabaseManager.getConnection()){
            addData(conn);
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

    private void checkData(Connection conn) throws Exception {
        ArrayList<String[]> requestExamples = sqlTestStatements.getSelectsAndExpected();
        for(String[] dataGroup : requestExamples){
            String actual = dataGroup[0];
            String expected = dataGroup[1];

            try (var actualToken = conn.prepareStatement(actual)) {
                var rs = actualToken.executeQuery();
                rs.next();
                var result = rs.getString(1);

                if(!(expected.equals(result))){
                    throw new Exception("authToken not equal");
                }
            }
        }
    }

    private void addData(Connection conn) throws Exception{
        ArrayList<String> addExamples = sqlTestStatements.getAddExamples();
        for(String insert : addExamples){
            try (var preparedStatement = conn.prepareStatement(insert)) {
                preparedStatement.executeUpdate();
            }
        }
    }
}
