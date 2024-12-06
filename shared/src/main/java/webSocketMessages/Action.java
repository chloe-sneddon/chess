package webSocketMessages;
import com.google.gson.Gson;

public record Action(Type type, String playerName, String moveDes) {
    public enum Type {
        JOIN,
        OBSERVE,
        LEAVE,
        RESIGN,
        CHECK,
        CHECKMATE,
        MOVE
    }

    public String toString() {
        return new Gson().toJson(this);
    }

}
