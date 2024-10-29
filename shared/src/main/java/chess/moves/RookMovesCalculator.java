package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class RookMovesCalculator extends MovesCalculator {
    private Collection<ChessMove> possibleMoves;

    RookMovesCalculator(){}
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPiece piece, ChessPosition startPosition){

        possibleMoves = new ArrayList<>();

        possibleMoves.addAll(calcMoves(1,0,startPosition,board,piece));
        possibleMoves.addAll(calcMoves(-1,0, startPosition,board,piece));
        possibleMoves.addAll(calcMoves(0,-1, startPosition,board,piece));
        possibleMoves.addAll(calcMoves(0,1, startPosition,board,piece));

        return possibleMoves;
    }
}