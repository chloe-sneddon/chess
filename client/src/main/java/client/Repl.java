package client;

import client.websocket.NotificationHandler;
import websocket.commands.JoinGameCommand;
import websocket.messages.JoinGameMessage;
import websocket.messages.ServerMessage;

import javax.management.Notification;
import com.google.gson.Gson;
import static ui.EscapeSequences.*;
import java.util.Scanner;

public class Repl implements NotificationHandler {
    private final ChessClient client;
//    private new Gson() serializer;

    public Repl(String serverURL){
        client = new ChessClient(serverURL, this);
    }

    public void run(){
        System.out.println(SET_TEXT_COLOR_BLUE + "Welcome to chess! \n\n");
        System.out.print(client.help());

        Scanner scanner = new Scanner(System.in);
        var result = "";

        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                System.out.print(SET_TEXT_COLOR_BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(SET_TEXT_COLOR_RED + msg);
            }
        }
        System.out.println();
    }

    public void notify(ServerMessage notification){
        System.out.println(SET_TEXT_COLOR_BLUE + notification.getMessage());
        printPrompt();
    }
//    private String deserialize(ServerMessage.ServerMessageType type, String jsonMsg){
//        return new Gson().fromJson(jsonMsg, JoinGameMessage.class);
////                fromjson(jsonMsg, JoinGameMessage);
//    }

    private void printPrompt() {
        System.out.print("\n" + SET_TEXT_COLOR_BLUE + ">>> " + SET_TEXT_COLOR_GREEN + CURSOR_BLINK);
    }
}
