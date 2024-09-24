package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType pieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        ChessPiece piece = board.getPiece(myPosition);

        switch (pieceType){
            case KING:
                KingMovesCalculator king = new KingMovesCalculator();
                moves = king.CalculateMoves(board,piece,myPosition);
                break;
            case PAWN:
                PawnMoveCalculator pawn = new PawnMoveCalculator();
                moves = pawn.CalculateMoves(board,piece,myPosition);
                break;
            case QUEEN:
                QueenMovesCalculator queen = new QueenMovesCalculator();
                moves = queen.CalculateMoves(board,piece,myPosition);
                break;
            case BISHOP:
                BishopMovesCalculator bishop = new BishopMovesCalculator();
                moves = bishop.CalculateMoves(board,piece,myPosition);
                break;
            case KNIGHT:
                KnightMovesCalculator knight = new KnightMovesCalculator();
                moves = knight.CalculateMoves(board,piece,myPosition);
                break;
            case ROOK:
                RookMovesCalculator rook = new RookMovesCalculator();
                moves = rook.CalculateMoves(board,piece,myPosition);
                break;
        }

            return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "pieceColor=" + pieceColor +
                ", pieceType=" + pieceType +
                '}';
    }
}
