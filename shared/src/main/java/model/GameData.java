package model;

import chess.ChessGame;

//gameID	int
//whiteUsername	String
//blackUsername	String
//gameName	String
//game	ChessGame
public record GameData(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {}
