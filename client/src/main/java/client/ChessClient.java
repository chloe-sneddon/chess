package client;

import client.websocket.NotificationHandler;

import java.util.Arrays;

public class ChessClient {
    private final ServerFacade server;
    private final String serverUrl;
    private final NotificationHandler notificationHandler;
    private State state = State.SIGNEDOUT;

    ChessClient(String serverURL, NotificationHandler notificationHandler){
        server = new ServerFacade(serverURL);
        this.serverUrl = serverURL;
        this.notificationHandler = notificationHandler;
    }

    public String eval(String input) {
        try {
            var tokens = input.split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            cmd = cmd.toLowerCase();
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
//            TODO: fix this so that commands can only be called during a specific state (pre login, post login, etc)
            return switch (cmd) {
                case "login" -> signIn(params);
                case "register" -> register(params);
                default -> help();
            };
        }
        catch (ResponseException e) {
            return e.getMessage();
        }
    }

    public String register(String... params) throws ResponseException{
        if (params.length >= 1) {
            String username = params[0];
            String password = params[1];
            String email = params[2];
            server.register(username,password,email);
            state = State.SIGNEDIN;
            return String.format("Welcome to chess %s.", username) + "\n\n" + help();
        }
        throw new ResponseException(400, "Expected: <Username> <Password>");
    }

    public String signIn(String... params) throws ResponseException {
        if (params.length >= 1) {

            String username = params[0];
            String password = params[1];
            server.login(username,password);
            state = State.SIGNEDIN;

            return String.format("You signed in as %s.", username) + "\n\n" + help();
        }
        throw new ResponseException(400, "Expected: <Username> <Password>");
    }

    public String help() {
        switch(state) {
            case State.SIGNEDOUT:
                return """
                        
                        |              COMMAND                   |        EXPLANATION
                        |----------------------------------------------------------------------------
                        | login <USERNAME> <PASSWORD>            |  login to an existing account
                        | register <USERNAME> <PASSWORD> <EMAIL> |  create an account
                        | quit                                   |  quit playing chess
                        | help                                   |  display possible commands
                        |                                        |
                        
                        """;

            case State.SIGNEDIN:
                return """
                        
                        |              COMMAND                   |        EXPLANATION
                        ------------------------------------------------------------------------------
                        | create <NAME>                          |  create a chess game
                        | join <GAMEID> [BLACK|WHITE]            |  join a game as a specific color
                        | list                                   |  list active games
                        | observe <GAMEID>                       |  observe active game
                        | logout                                 |  logout of session
                        | quit                                   |  quit playing chess
                        | help                                   |  display possible commands
                        
                        """;

            case State.INGAME:
                return """
                
                        |              COMMAND                   |        EXPLANATION
                        ------------------------------------------------------------------------------
                        | help                                   |  display possible commands
                
                        """;

            default:
                return "Error: unauthorized";
        }
    }
}
