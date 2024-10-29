package chess.moves;

import chess.*;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMovesCalculator {
    private Collection<ChessMove> moves;
    QueenMovesCalculator(){
        moves = new ArrayList<>();
    }
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPiece piece, ChessPosition myPosition){
        BishopMovesCalculator diagonalMoves = new BishopMovesCalculator();
        RookMovesCalculator straightMoves = new RookMovesCalculator();
        moves = diagonalMoves.calculateMoves(board,piece,myPosition);
        moves.addAll(straightMoves.calculateMoves(board,piece,myPosition));
        return moves;
    }


}
