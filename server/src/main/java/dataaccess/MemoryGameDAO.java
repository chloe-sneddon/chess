package dataaccess;

import chess.ChessGame;
import model.GameData;
import service.UserServiceException;

import java.util.HashMap;

public class MemoryGameDAO implements GameDAO {
    private HashMap<Integer, GameData> allGameData = new HashMap <Integer, GameData>();

    public void clear() {
        allGameData.clear();
    }
    public int createGame(String gameName) throws UserServiceException{

        if((gameName == null)|(gameName.isEmpty())){
            throw new UserServiceException("Error: no provided gameName",500);
        }
        var gameId = createGameID();
        GameData gmData = new GameData(gameId,null,null,gameName,new ChessGame());
        allGameData.put(gameId,gmData);
        return gmData.gameID();
    }
    private int createGameID(){
//        generate random ID
        return 10;
    }
    public GameData getGameData(int gameID){
        return allGameData.get(gameID);
    }
}
