package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMovesCalculator extends MovesCalculator {
    private Collection<ChessMove> possibleMoves;

    BishopMovesCalculator() {}
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPiece piece, ChessPosition startPosition){
        possibleMoves = new ArrayList<>();

        possibleMoves.addAll(calcMoves(1,1,startPosition,board,piece));
        possibleMoves.addAll(calcMoves(-1,1, startPosition,board,piece));
        possibleMoves.addAll(calcMoves(1,-1, startPosition,board,piece));
        possibleMoves.addAll(calcMoves(-1,-1, startPosition,board,piece));

        return possibleMoves;
    }
}
