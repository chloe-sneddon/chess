package dataaccess.game;

import com.google.gson.Gson;
import chess.ChessGame;

public class GameUpdate {
    private final Gson serializer = new Gson();

    public ChessGame deserializeGame(String serializedObject){
        return serializer.fromJson(serializedObject, ChessGame.class);
    }
    public String serialize(ChessGame game){
        return serializer.toJson(game);
    }

}
