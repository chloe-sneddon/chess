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
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        var serverUrl = "http://localhost:" + port;
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

    @Test
    @DisplayName("List Games")
    public void listGames(){
        try{
            sf.register("userOne","passwordOne","email");
            sf.createGame("gameOne");
            sf.createGame("gameTwo");
            var list = sf.listGames();
            var expected = 2;
            Assertions.assertEquals(expected,list.size());
        }
        catch (Exception e) {
            Assertions.fail("Unexpected error: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("empty list Games")
    public void listGamesBad(){
        try{
            sf.register("userOne","passwordOne","email");
            var list = sf.listGames();
            var expected = 0;
            Assertions.assertEquals(expected,list.size());
        }
        catch (Exception e) {
            Assertions.fail("Unexpected error: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("join game")
    public void joinGame(){
        try{
            sf.register("userOne","passwordOne","email");
            sf.createGame("gameOne");
            var list = sf.listGames();
            var gameID = list.get(0).gameID();
            sf.joinGame(gameID, "WHITE");
        }
        catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("bad join game")
    public void joinGameBad(){
        try{
            sf.register("userOne","passwordOne","email");
            sf.joinGame(12, "WHITE");
            Assertions.fail("Expected a thrown error");
        }
        catch (Exception e) {
            var expected = "Error: bad request";
            Assertions.assertEquals(expected,e.getMessage());
        }
    }

    @Test
    @DisplayName("logout")
    public void logout(){
        try{
            sf.register("userOne","passwordOne","email");
            sf.logout();
            sf.createGame("random");
            Assertions.fail("Expected an error");
        }
        catch (Exception e) {
            var expected = "Error: unauthorized";
            Assertions.assertEquals(expected,e.getMessage());
        }
    }

    @Test
    @DisplayName("logout Bad")
    public void logoutBad(){
        try{
            sf.logout();
            Assertions.fail("Expected an error");
        }
        catch (Exception e) {
            var expected = "Error: unauthorized";
            Assertions.assertEquals(expected,e.getMessage());
        }
    }

}
