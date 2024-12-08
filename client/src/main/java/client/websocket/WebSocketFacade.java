package client.websocket;

import client.GameHandler;
import client.ResponseException;
import com.google.gson.Gson;
import org.eclipse.jetty.util.Scanner;
import websocket.commands.JoinGameCommand;
import websocket.commands.UserGameCommand;
import websocket.messages.ServerMessage;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;


public class WebSocketFacade extends Endpoint {

    private final Gson serializer = new Gson();
    Session session;
    private NotificationHandler notificationHandler;

//    public static void main(String[] args) throws Exception {
//        var ws = new WebSocketFacade();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter a message you want to echo");
//        while (true) ws.send(scanner.nextLine());
//    }

    public WebSocketFacade(String url, NotificationHandler notificationHandler) throws ResponseException {
        try{
            url = url.replace("http","ws");  // replaces http with websocket redirect
            URI socketURI = new URI(url + "/ws");
            this.notificationHandler = notificationHandler;

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this,socketURI);

            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    ServerMessage notification = new Gson().fromJson(message, ServerMessage.class);
                    notificationHandler.notify(notification);
                }
            });
        }
        catch(Exception e){
            throw new ResponseException(500, e.getMessage());
        }
    }

    @Override
    public void onOpen(javax.websocket.Session session, EndpointConfig endpointConfig) {}

    @Override
    public void onError(javax.websocket.Session session, Throwable thr) {
        super.onError(session, thr);
    }

    @Override
    public void onClose(javax.websocket.Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
    }

//    outgoing messages
    public void connect(String playerColor,String authToken, Integer gameID) throws ResponseException{
        try{
            var tmp = new JoinGameCommand(UserGameCommand.CommandType.CONNECT,authToken,gameID,playerColor);
            String msg = serializer.toJson(tmp);
            this.session.getBasicRemote().sendText(msg);
        }
        catch(IOException e){
            throw new ResponseException(500, e.getMessage());
        }
    }

//    connect()
//    makeMove()
//    leaveGame()
//    resignGame()

//    private sendMessages() {
//      1. create command messages
//      2. send them to server
//    }

//    process incoming messages
//    onMessage(message){
//      1.deserialize message
//      2. call gameHandler to process
//    }

}
