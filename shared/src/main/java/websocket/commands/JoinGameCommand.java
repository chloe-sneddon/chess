package websocket.commands;

import java.util.Objects;

public class JoinGameCommand {
    private final UserGameCommand.CommandType commandType;
    private final String authToken;
    private final Integer gameID;
    private final String playerColor;

    public JoinGameCommand(UserGameCommand.CommandType commandType, String authToken, Integer gameID, String playerColor) {
        this.commandType = commandType;
        this.authToken = authToken;
        this.gameID = gameID;
        this.playerColor = playerColor;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Integer getGameID() {
        return gameID;
    }

    public String getPlayerColor(){ return playerColor;}

    public UserGameCommand.CommandType getCommandType (){return commandType;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinGameCommand that = (JoinGameCommand) o;
        return Objects.equals(authToken, that.authToken) && Objects.equals(gameID, that.gameID) && Objects.equals(playerColor, that.playerColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken, gameID, playerColor);
    }
}
