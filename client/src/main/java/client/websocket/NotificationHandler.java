package client.websocket;

//import webSocketMessages.ServerMessage;
//import websocket.messages.ServerMessage;

public interface NotificationHandler {
    void notify(websocket.messages.ServerMessage notification);

}
