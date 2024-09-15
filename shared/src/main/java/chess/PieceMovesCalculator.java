package chess;

import java.util.Collection;


public interface PieceMovesCalculator {
    //    private ChessPiece piece;
    Collection <ChessMove> pieceMoves(ChessBoard board, ChessPosition position);

}
