package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Collection;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoard() {}

    public void movePiece(ChessMove newMove,ChessPiece endPiece){
//        TODO: Keep track of captured pieces (uncomment capturedPiece and if statement)
//        ChessPiece capturedPiece = null;

//        remove piece from start position
        removePiece(newMove.getStartPosition());

////        check to see if there is a piece in the new position
//        if(getPiece(newMove.getEndPosition()) != null){
//            capturedPiece = getPiece(newMove.getEndPosition());
//        }
//        add piece to new position
        addPiece(newMove.getEndPosition(),endPiece);

    }

    public void setBoard (ChessBoard copy){
        clearBoard();

        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                ChessPosition tmpPosition = new ChessPosition(i,j);
                ChessPiece tmpPiece = copy.getPiece(tmpPosition);
                addPiece(tmpPosition,tmpPiece);
            }
        }

    }
    public void clearBoard(){
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
            ChessPosition tmpPosition = new ChessPosition(i,j);
            removePiece(tmpPosition);
            }
        }
    }

    public ChessPiece[][] getBoard(){
        return this.board;
    }
    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow() - 1][position.getColumn() -1] = piece;
    }
    public void removePiece(ChessPosition position){
        board[position.getRow() - 1][position.getColumn() -1] = null;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {

        return board[position.getRow()-1][position.getColumn()-1];
    }

    public Collection <ChessPosition> getTeamPositions(ChessGame.TeamColor color){
        Collection <ChessPosition> positionsList = new ArrayList<>();
//        iterate through board and find each piece location of a specific color,
//        add it to the list if its of the same color
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 9; j++){
                ChessPosition tmpPosition = new ChessPosition(i,j);
//                  if piece isnt null then can check for matching team color
                 if((getPiece(tmpPosition) != null) && (getPiece(tmpPosition).getTeamColor() == color)){
                    positionsList.add(tmpPosition);
                }
            }
        }
        return positionsList;
    }
    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        DefaultBoard resetBoard = new DefaultBoard();
        ChessBoard newBoard = resetBoard.SetDefaultBoard();
        this.board = newBoard.getBoard();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        String returnVal = "";
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(board[i][j] == null){
                    returnVal = returnVal + "row: " + i+1 + " col: " + j+1 + " [null] ";
                }
                else{
                    returnVal = returnVal + "row: " + i+1 + " col: " + j+1 + " [" + board[i][j].toString() + "] ";
                }
            }
        }
        return returnVal;
    }
}
