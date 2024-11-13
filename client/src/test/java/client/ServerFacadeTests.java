package client;

import org.junit.jupiter.api.*;
import server.Server;
import service.GeneralService;


public class ServerFacadeTests {

    private static Server server;
    private static ServerFacade sf;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(8080);
        System.out.println("Started test HTTP server on " + port);
        var serverUrl = "http://localhost:8080";
        sf = new ServerFacade(serverUrl);
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }

    @BeforeEach
    public void set() throws Exception{
        GeneralService.clear();
    }


    @Test
    @DisplayName("Register good")
    public void register(){
        try{
            sf.register("userOne","passwordOne","email");
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @DisplayName("Register bad")
    public void registerBad(){
        try{
            sf.register("userOne","passwordOne","email");
            sf.register("userOne","passwordOne","email");
            Assertions.fail("Expected exception not thrown");
        } catch (ResponseException e) {
            int expectedCode = 403;
            String expectedMessage = "Error: already taken";
            Assertions.assertEquals(expectedMessage,e.getMessage());
            System.out.println("Correct Exception thrown");
        }
        catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Login")
    public void login() {
        try{
            sf.register("userOne","passwordOne","email");
            sf.login("userOne","passwordOne");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Login bad")
    public void loginBad() {
        try{
            sf.register("userOne","passwordOne","email");
            sf.login("userOne","wrongPassword");
            Assertions.fail("Expected error not thrown");
        }
        catch (Exception e) {
            String expectedMessage = "Error: unauthorized";
            Assertions.assertEquals(expectedMessage,e.getMessage());
        }
    }

    @Test
    @DisplayName("Create game")
    public void createGame(){
        try{
            sf.register("userOne","passwordOne","email");
            sf.createGame("gameOne");
        }
        catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Bad Create game")
    public void createGameBad(){
        try{
            sf.createGame("gameOne");
            Assertions.fail("Expected error");
        }
        catch (ResponseException e) {
            String expected = "Error: unauthorized";
            Assertions.assertEquals(expected,e.getMessage());
        }
    }

}
