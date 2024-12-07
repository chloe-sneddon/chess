package websocket.messages;

import java.util.Objects;

public class JoinGameMessage {
    private final ServerMessage.ServerMessageType serverMessageType;
    private final String displayMessage;

    public JoinGameMessage(ServerMessage.ServerMessageType type, String displayMessage) {
        this.serverMessageType = type;
        this.displayMessage = displayMessage;
    }

    public ServerMessage.ServerMessageType getServerMessageType() {
        return this.serverMessageType;
    }

    public String getDisplayMessage(){
        return this.displayMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinGameMessage that = (JoinGameMessage) o;
        return serverMessageType == that.serverMessageType && Objects.equals(displayMessage, that.displayMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverMessageType, displayMessage);
    }
}
