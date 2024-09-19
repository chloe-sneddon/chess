package chess;
//this is me testing commit to git using the github desktop app

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] squares = new ChessPiece[8][8];

    public ChessBoard() {
//        squares = null;
//      initializeBoard();
    }
//    public ChessBoard(ChessBoard copy) {
//        this(copy.value);
//    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }
    public void removePiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = null;
    }


    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {

        return squares[position.getRow() -1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
//        clear board
        this.squares = new ChessPiece[8][8];
        AutomaticPieceLocations boardReset = new AutomaticPieceLocations(this);
        boardReset.resetBoard();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }

    @Override
    public String toString() {
//        String[][] lines = new String[8][8];
        String lines = "";
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(squares[i][j] == null){
                    lines = lines + "null";
                }
                else {
                    lines = lines + squares[i][j].toString();
                }
            }
        }
        return lines;
    }
}
