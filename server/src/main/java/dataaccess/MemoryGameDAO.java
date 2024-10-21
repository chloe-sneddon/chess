package dataaccess;

import model.GameData;

import java.util.HashMap;

public class MemoryGameDAO implements GameDAO {
    private HashMap<String, GameData> allGameData = new HashMap <String, GameData>();
    public void clear() {
        allGameData.clear();
    }
}
