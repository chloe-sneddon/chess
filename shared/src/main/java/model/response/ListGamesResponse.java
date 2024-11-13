package model.response;
import model.GameData;
import java.util.ArrayList;

/*
 * Class Model for ListGames Response
 */
public record ListGamesResponse (ArrayList<GameData> games) {}
