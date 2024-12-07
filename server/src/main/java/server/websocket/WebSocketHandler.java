package server.websocket;

import com.google.gson.Gson;
import dataaccess.auth.AuthDAO;
import exception.ResponseException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
//import websocket.Action;
//import websocket.Notification;
import service.GameService;
import service.GeneralService;
import service.ServiceException;
import websocket.commands.JoinGameCommand;
import websocket.commands.UserGameCommand;
import websocket.messages.JoinGameMessage;
import websocket.messages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;

@WebSocket
public class WebSocketHandler {
    private final WebsocketSessions connections = new WebsocketSessions();
    private final Gson serializer = new Gson();

//    @OnWebSocketConnect
//    public void onConnect(Session session){
////        connections.g
//    }
//
//    @OnWebSocketClose
//    public void onClose(Session session){
//
//    }
//
//    @OnWebSocketError
//    public void onError(Throwable throwable){
//
//    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
//       1.  determine message type
//       2.  call a method to process

        UserGameCommand action = serializer.fromJson(message, UserGameCommand.class);

        switch (action.getCommandType()) {
            case CONNECT -> connect(message, session);
//            case MAKE_MOVE -> enter(action.getGameID(), session);
//            case LEAVE -> enter(action.getGameID(), session);
//            case RESIGN -> enter(action.getGameID(), session);
        }
    }

//    connect(message) call service and/or send messages to clients
    public void connect(String message, Session session) throws IOException{
        JoinGameCommand action = serializer.fromJson(message, JoinGameCommand.class);
        connections.addSessionToGame(action.getGameID(), session);
        String returnVal = "";
        ServerMessage notification;

        try{
            GameService.joinGame(action.getAuthToken(),action.getGameID(), action.getPlayerColor());
            var usrname = GeneralService.AUTHDATA.getUsername(action.getAuthToken());

            notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION);
            returnVal = String.format("%s joined the game as %s", usrname, action.getPlayerColor());
        }
        catch (Exception e) {
            notification = new ServerMessage(ServerMessage.ServerMessageType.ERROR);
            returnVal = "ERROR: an error occurred -> CONNECT.WEBSOCKETHANDLER";
        }

        String msg = serializer.toJson(new JoinGameMessage(notification.getServerMessageType(),returnVal));
        broadcast(action.getGameID(),msg);
    }

    public void broadcast(int gameID, String message) throws IOException {
        var removeList = new ArrayList<Session>();

        for (Session s : connections.getSessionsForGame(gameID)) {
                if (s.isOpen()) {
                    sendMessage(message,s);

                } else {
                    removeList.add(s);
                }
        }

        // Clean up any connections that were left open.
        for (Session s : removeList) {
            connections.removeSessionFromGame(gameID,s);
        }
    }


//    makeMove(message) service.method(...)
//    leaveGame(message) sendMessage(...)
//    resignGame(message) broadcastMessage(...)

    public void sendMessage(String message, Session session) throws IOException{
        session.getRemote().sendString(message);
    }
//    broadcastMessage(gameID, message, exceptThisSession) -> see websocketSessions


//    private void enter(int gameID, Session session) throws IOException {
//        connections.addSessionToGame(gameID, session);
//        var message = String.format("%s is in the shop", gameID);
//        var notification = new Notification(Notification.Type.ARRIVAL, message);
//        connections.broadcast(gameID, notification);
//    }
//
//    private void exit(int gameID, Session session) throws IOException {
//        connections.removeSessionFromGame(gameID,session);
//        var message = String.format("%s left the shop", gameID);
//        var notification = new Notification(Notification.Type.DEPARTURE, message);
//        connections.broadcast(gameID, notification);
//    }
//
//    public void makeNoise(String petName, String sound) throws ResponseException {
//        try {
//            var message = String.format("%s says %s", petName, sound);
//            var notification = new Notification(Notification.Type.NOISE, message);
//            connections.broadcast("", notification);
//        } catch (Exception ex) {
//            throw new ResponseException(500, ex.getMessage());
//        }
//    }
}
