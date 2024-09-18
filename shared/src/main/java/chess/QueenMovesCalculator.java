package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class QueenMovesCalculator implements PieceMovesCalculator{
    private Collection<ChessMove> chessMoveCollection;
    private ChessPosition permStart;
    private ChessBoard chessBoard;
    private ChessPiece chessPiece;

    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        chessBoard = board;
        permStart = position;
        chessPiece = board.getPiece(position);
//        calculate diagonal moves
        BishopMovesCalculator diagonalMoves = new BishopMovesCalculator();
        chessMoveCollection = diagonalMoves.pieceMoves(chessBoard, permStart);
        System.out.println(chessMoveCollection);
//        calculate straight moves
        RookMovesCalculator rookMoves = new RookMovesCalculator();
        chessMoveCollection.addAll(rookMoves.pieceMoves(chessBoard, permStart));

        return chessMoveCollection;
    }
}