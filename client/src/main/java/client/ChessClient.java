package client;

import client.websocket.NotificationHandler;
import client.websocket.WebSocketFacade;
import ui.RenderBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ui.EscapeSequences.ERASE_SCREEN;
import static ui.EscapeSequences.SET_TEXT_BOLD;

public class ChessClient {
    private final ServerFacade server;
    private State state = State.SIGNEDOUT;
    private RenderBoard gameBoard;
    private WebSocketFacade ws;
    final private String serverUrl;
    final private NotificationHandler notificationHandler;
//     Game num for user view, GameID as stored in Server
    private HashMap<Integer, Integer> idFromServer;
    private HashMap<Integer,Integer> idFromUser;
    private int numGames;

    ChessClient(String serverURL, NotificationHandler notificationHandler){
        this.serverUrl = serverURL;
        this.notificationHandler = notificationHandler;
        server = new ServerFacade(serverURL);
        idFromServer = new HashMap<Integer, Integer>();
        idFromUser = new HashMap<Integer, Integer>();
        numGames = 1;
    }

    public String eval(String input) {
        try {
            var tokens = input.split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            cmd = cmd.toLowerCase();
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
//            TODO: fix this so that commands can only be called during a specific state (pre login, post login, etc)

            return switch (cmd) {
//                pre-login
                case "login" -> signIn(params);
                case "register" -> register(params);
                case "quit" -> "quit";
//                post-login
                case "create"-> createGame(params);
                case "list"-> listGames();
                case "join"-> joinGame(params);
                case "observe" -> observeGame(params);
                case "logout" -> logout();
//                in-Game
                case "redraw" -> redraw();
                case "resign" -> back();
                case "leave" -> back();
                case "back" -> back();
                case "move" -> back();
                case "highlight" -> back(); 

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

        if (params.length == 3) {
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
        if (params.length == 2) {
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
        if (params.length == 1) {
            String gameName = params[0];

            var game = server.createGame(gameName);
            idFromServer.put(game.gameID(),numGames);
            idFromUser.put(numGames,game.gameID());
            numGames++;

            return String.format("Game %s created", gameName) + "\n\n";
        }
        throw new ResponseException(400, "Not a valid command");
    }

    public String listGames() throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
        var gameList = server.listGames();
        String returnVal = "Games ";
        for(int i = 0; i<gameList.size();i++){
            var game = gameList.get(i);
            var userID = idFromServer.get(game.gameID());
            if( userID == null){
                idFromServer.put(game.gameID(),numGames);
                idFromUser.put(numGames,game.gameID());
                numGames++;
            }
            String gameID = "Game ID:                  " + idFromServer.get(game.gameID());
            String gameName = "Game Name:                " + game.gameName();
            String white = "White player Username:    " + game.whiteUsername();
            String black = "Black player Username:    " + game.blackUsername();

            returnVal += "\n_________________________________\n"
                    + gameID + "\n" + gameName
                    + "\n" + white + "\n" + black;
        }
        return returnVal + "\n\n";
    }

    public String joinGame(String... params) throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
//        http join game
        if (params.length == 2){
            try {
                var i = Integer.parseInt(params[0]);
                var gameID = idFromUser.get(i);
                var playerColor = params[1];

                server.joinGame(gameID, playerColor);

                state = State.INGAME;
                System.out.print(ERASE_SCREEN);

//              TODO: WEBSOCKET CALL HERE!!
                gameBoard = new RenderBoard();
                gameBoard.run(playerColor);

                ws = new WebSocketFacade(serverUrl, notificationHandler);
                ws.connect(playerColor,server.getToken(),gameID);
                return "Game Joined!";
            } catch (Exception e) {
                throw new ResponseException(500, "Expected: <GAME_ID> [BLACK|WHITE]");
            }
        }
        throw new ResponseException(500, "Not a valid command");
    }

    public String observeGame(String... params) throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
        if(params.length == 1){
//            TODO: WEBSOCKET GOES HERE AND CAN DELETE NEW RENDERBOARD CALL
            gameBoard = new RenderBoard();
            gameBoard.run("WHITE");
            return "";
        }
        throw new ResponseException(500, "Not a valid command");
    }

    public String logout() throws ResponseException{
        if(state != State.SIGNEDIN){
            throw new ResponseException(500, "Not a valid command");
        }
        server.logout();
        state = State.SIGNEDOUT;
        return help();
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
                        -----------------------------------------------------------------------------
                        | help                                   |  display possible commands
                        | redraw                                 |  redraw chess board
                        | leave                                  |  leave game
                        | move <START POSITION> <END POSITION>   |  make move on board
                        | resign                                 |  forfeit game
                        | highlight <CHESS POSITION>             |  highlight legal moves of piece
                
                        """;

            default:
                return "Error: unauthorized";
        }
    }
    public String back() throws ResponseException{
        if(state == State.SIGNEDOUT){
            throw new ResponseException(500, "Not a valid command");
        }
        else if(state == State.SIGNEDIN){
            state = State.SIGNEDOUT;
        }
        else if(state == State.INGAME){
            state = State.SIGNEDIN;
        }
        return help();
    }
}
