package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.io.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessBoard board;
    private ChessPosition myPosition;
    private final ChessGame.TeamColor TeamColor;
    private final PieceType PieceType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
//       TODO: set chessBoard and ChessPosition using an override function?
//             *note: board and position are changeable throughout the game
//                    and are called in pieceMoves method below
        this.TeamColor = pieceColor;
        this.PieceType = type;
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
        return TeamColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return PieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        this.board = board;
        this.myPosition = myPosition;
        Collection<ChessMove> moves = new ArrayList<>();
//        TODO: fix this collection so it returns something

        System.out.println("--- in pieceMoves() ");

//       TODO: Figure out which pieceType it is and which class to call

        BishopMovesCalculator bishop = new BishopMovesCalculator();
        bishop.pieceMoves(this.board, this.myPosition);

        return moves;
//        throw new RuntimeException("Not implemented");
//        return new ArrayList<ChessMove>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return TeamColor == that.TeamColor && PieceType == that.PieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TeamColor, PieceType);
    }

    @Override
    public String toString() {
        return "ChessPiece{" +
                "board=" + board +
                ", myPosition=" + myPosition +
                ", TeamColor=" + TeamColor +
                ", PieceType=" + PieceType +
                '}';
    }
//    private


}
