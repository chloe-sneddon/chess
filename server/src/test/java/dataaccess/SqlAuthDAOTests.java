package dataaccess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import service.GeneralService;

public class SqlAuthDAOTests extends SqlTestFunctions{

    @Test
    @DisplayName("Initialize DB")
    public void initializeDB(){

        try (var conn = DatabaseManager.getConnection()) {
            String dbName = DatabaseManager.getDatabaseName();
            String drop = "Drop Database " + dbName + ";";

            try (var actualToken = conn.prepareStatement(drop)) {
                actualToken.executeUpdate();
            } catch (Exception e) {
                Assertions.fail(e.getLocalizedMessage());
            }
        }
        catch (Exception e){
            Assertions.fail("unexpected loss of connection");
        }
        try{
            DatabaseManager.configureDatabase();
        }
        catch(Exception e){
            Assertions.fail("Database not configured: " + e.getLocalizedMessage());
        }
        try (var conn = DatabaseManager.getConnection()) {
            addData(conn);
            checkData(conn);
        }
        catch(Exception e){
            Assertions.fail("this exception " + e.getLocalizedMessage());
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

    @Test
    @DisplayName("AddAuthData")
    public void addAuthData(){
        try{
            authSql.addAuthData("123authToken","Puddles");
            String expectedToken = "123authToken";

            try (var conn = DatabaseManager.getConnection()) {
                String statement = "Select authToken from authData;";

                try (var actualToken = conn.prepareStatement(statement)) {
                    var rs = actualToken.executeQuery();
                    rs.next();
                    var result = rs.getString(1);

                    if(!(expectedToken.equals(result))){
                        throw new Exception("authToken not equal");
                    }
                }
            }
        }
        catch(Exception e){
            Assertions.fail("Unexpected Exception: " + e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Negative AddAuthData")
    public void negAddAuthDta(){
        try(var conn = DatabaseManager.getConnection()){
            addData(conn);
            authSql.addAuthData("123authToken","Puddles");
            Assertions.fail("failed");
        }
        catch (Exception e){
            System.out.println("Passed Failed Test");
        }

    }

    @Test
    @DisplayName("Get Username from AuthToken")
    public void getUsername(){
        try(var conn = DatabaseManager.getConnection()) {
            addData(conn);
            String expected = "Puddles";
            String token = "123authToken";
            String actual = authSql.getUsername(token);
            Assertions.assertEquals(expected,actual);
        } catch (Exception e) {
            Assertions.fail("Unexpected error: " + e.getLocalizedMessage());
        }
    }

    @Test
    @DisplayName("Bad Get Username from authToken")
    public void negGetUsername(){
        try{
            String token = "123authToken";
            authSql.getUsername(token);
            Assertions.fail("Expected error");
        } catch (Exception e) {
            System.out.println("Passed failed test");
        }
    }

    @Test
    @DisplayName("Delete Token positive and negative test")
    public void deleteToken(){
        try(var conn = DatabaseManager.getConnection()){
            addData(conn);
            authSql.deleteToken("123authToken");
        }
        catch(Exception e){
            Assertions.fail(e.getLocalizedMessage());
        }
        try{
            authSql.getUsername("123authToken");
            Assertions.fail("expected thrown error");
        }
        catch(Exception e){
            System.out.println("Correct Thrown Exception");
        }
    }
}
