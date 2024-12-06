package webSocketMessages;

import com.google.gson.Gson;

public record Notification(Type type, String playerName) {
    public enum Type {
        JOIN,
        OBSERVE,
        LEAVE,
        RESIGN,
        CHECK,
        CHECKMATE
    }

    public String toString() {
        return new Gson().toJson(this);
    }

}
