package dataaccess;
//createGame: Create a new game.
//getGame: Retrieve a specified game with the given game ID.
//listGames: Retrieve all games.
//updateGame: Updates a chess game. It should replace the chess game string corresponding to a given gameID. This is used when players join a game or when a move is made.

import model.GameData;
import service.ServiceException;

import java.util.ArrayList;

public interface GameDAO {
    void clear();
    int createGame(String gameName) throws ServiceException;
    GameData getGameData(int gameID);
    ArrayList<GameData> getActiveGames();
}
