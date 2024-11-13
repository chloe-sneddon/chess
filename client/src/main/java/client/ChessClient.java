package client;

import client.websocket.NotificationHandler;

import java.util.Arrays;

public class ChessClient {
    private final ServerFacade server;
    private final String serverUrl;
    private State state = State.SIGNEDOUT;

    ChessClient(String serverURL, NotificationHandler notificationHandler){
        server = new ServerFacade(serverURL);
        this.serverUrl = serverURL;
    }

    public String eval(String input) {
        try {
            var tokens = input.split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            cmd = cmd.toLowerCase();
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
//            TODO: fix this so that commands can only be called during a specific state (pre login, post login, etc)

            return switch (cmd) {
//                prelogin
                case "login" -> signIn(params);
                case "register" -> register(params);
                case "quit" -> "quit";
//                postlogin
                case "create"-> createGame(params);
                case "list"-> listGames();
                case "join"-> joinGame(params);
                case "observe" -> observeGame(params);
                case "logout" -> logout(params);
//                in Game
                case "redraw" -> redraw();
                default -> help();
            };
        }
        catch (ResponseException e) {
            return e.getMessage();
        }
    }

    public String register(String... params) throws ResponseException{
        if(state != State.SIGNEDOUT){
            throw new ResponseException(500, "You are already logged in");
        }

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
        if(state != State.SIGNEDOUT){
            throw new ResponseException(500, "You are already logged in");
        }
        if (params.length >= 1) {
            String username = params[0];
            String password = params[1];
            server.login(username,password);
            state = State.SIGNEDIN;
            return String.format("You signed in as %s.", username) + "\n\n" + help();
        }
        throw new ResponseException(400, "Expected: <Username> <Password>");
    }

    public String createGame(String... params) throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
        if (params.length >= 1) {
            String gameName = params[0];
            server.createGame(gameName);
            return String.format("Game %s. created", gameName) + "\n\n" + help();
        }
        throw new ResponseException(400, "Expected: <Username> <Password>");
    }

    public String listGames() throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
        var gameList = server.listGames();
        return String.format("Games: %s.",gameList) + "\n\n" + help();
    }

    public String joinGame(String... params) throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
//        http join game
        if (params.length > 0){
            var gameID = Integer.parseInt(params[0]);
            var playerColor = params[1];
            server.joinGame(gameID,playerColor);
        }

        state = State.INGAME;
//        TODO: render board
        return "Game Joined!";
    }

    public String observeGame(String... params) throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
//        renderboard(); this is temporary for phase 5
        return "null";
    }

    public String logout(String... params) throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
        server.logout();
        state = State.SIGNEDOUT;
        return null;
    }

    public String redraw()throws ResponseException{
        if(state != State.INGAME){
            throw new ResponseException(500, "Not a valid command");
        }
        return null;
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
                        | create <GAME_NAME>                     |  create a chess game
                        | join <GAME_ID> [BLACK|WHITE]           |  join a game as a specific color
                        | list                                   |  list active games
                        | observe <GAME_ID>                      |  observe active game
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
