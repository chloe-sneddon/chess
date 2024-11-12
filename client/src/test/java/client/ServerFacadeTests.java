package client;

import org.junit.jupiter.api.*;
import server.Server;


public class ServerFacadeTests {

    private static Server server;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
    }
    
    public static void registerUser(){

    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    @DisplayName("Login")
    public void sampleTest() {
        String printEx = """
                        
                        |              COMMAND                   |        EXPLANATION
                        |----------------------------------------------------------------------------
                        | login <USERNAME> <PASSWORD>            |  login to an existing account
                        | register <USERNAME> <PASSWORD> <EMAIL> |  create an account
                        | quit                                   |  quit playing chess
                        | help                                   |  display possible commands
                        |                                        |
                        
                        """;
        System.out.println(printEx);

    }

}
