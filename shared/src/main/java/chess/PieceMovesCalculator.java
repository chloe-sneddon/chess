package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PieceMovesCalculator {

    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);
        ChessPiece.PieceType pieceType = piece.getPieceType();

        switch (pieceType) {
            case KING:
                KingMovesCalculator king = new KingMovesCalculator();
                moves = king.calculateMoves(board, piece, myPosition);
                break;
            case PAWN:
                PawnMoveCalculator pawn = new PawnMoveCalculator();
                moves = pawn.calculateMoves(board, piece, myPosition);
                break;
            case QUEEN:
                QueenMovesCalculator queen = new QueenMovesCalculator();
                moves = queen.calculateMoves(board, piece, myPosition);
                break;
            case BISHOP:
                BishopMovesCalculator bishop = new BishopMovesCalculator();
                moves = bishop.calculateMoves(board, piece, myPosition);
                break;
            case KNIGHT:
                KnightMovesCalculator knight = new KnightMovesCalculator();
                moves = knight.calculateMoves(board, piece, myPosition);
                break;
            case ROOK:
                RookMovesCalculator rook = new RookMovesCalculator();
                moves = rook.calculateMoves(board, piece, myPosition);
                break;
        }
        return moves;
    }

}
