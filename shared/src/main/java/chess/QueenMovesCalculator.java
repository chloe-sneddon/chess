package chess;

import java.util.ArrayList;
import java.util.Collection;

public class QueenMovesCalculator {
    private Collection<ChessMove> moves;
    QueenMovesCalculator(){
        moves = new ArrayList<>();
    }
    public Collection<ChessMove> CalculateMoves (ChessBoard board,ChessPiece piece,ChessPosition myPosition){
        BishopMovesCalculator diagonalMoves = new BishopMovesCalculator();
        RookMovesCalculator straightMoves = new RookMovesCalculator();
        moves = diagonalMoves.CalculateMoves(board,piece,myPosition);
        moves.addAll(straightMoves.CalculateMoves(board,piece,myPosition));
        return moves;
    }


}
