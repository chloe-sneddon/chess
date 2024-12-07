package client;

import chess.ChessGame;

public class GameHandler {
    private ChessGame game;

    GameHandler(){
//       TODO: initialize game as inital starting board here?
    }

    public void updateGame(ChessGame gm){
        this.game = gm;
    }

    public void printMessage(String message){
//        print the message to user screen
    }
}
