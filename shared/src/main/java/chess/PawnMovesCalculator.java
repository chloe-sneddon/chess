package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator implements PieceMovesCalculator {
    private Collection<ChessMove> chessMoveCollection;
    private ChessPosition permStart;
    private ChessBoard chessBoard;
    private ChessPiece chessPiece;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {

//        Calculate positive, positive diagonals
        permStart = position;
        chessBoard = board;
        chessMoveCollection = new ArrayList<>();
        chessPiece = board.getPiece(position);
//        diagonal moves
        int whiteMove = 1;
        int blackMove = -1;

        if(chessPiece.getTeamColor() == ChessGame.TeamColor.WHITE){
            calcMove(whiteMove);
        }
        else {
            calcMove(blackMove);
        }
        return chessMoveCollection;
    }

    private void calcMove(int LorR) {
//  calculate the move forward
        int row = permStart.getRow() + LorR;
        int column = permStart.getColumn();
        if(row > 8 | column > 8){
            return;
        }
        if(row < 1 || column < 1){
            return;
        }

        ChessPosition newPosition = new ChessPosition(row, column);

//      check to see if there is a piece directly in front
        if (chessBoard.getPiece(newPosition) == null) {
            ChessMove tmpMove = new ChessMove(permStart, newPosition, null);
            chessMoveCollection.add(tmpMove);

//          if () color is white and in start (row 2) then check for double
            if(chessPiece.getTeamColor() == ChessGame.TeamColor.WHITE){
//            if color at start position, and the double position is not blocked, add piece move to collection
                row = row + 1;
                ChessPosition doublePosition = new ChessPosition(row, column);
                if (permStart.getRow() == 2){
                    if(chessBoard.getPiece(doublePosition) == null){
                        ChessMove doubleMove = new ChessMove(permStart, doublePosition, null);
                        chessMoveCollection.add(doubleMove);
                    }
                }
            }
//            if () color is black and in start (row 7) then check for double
            if(chessPiece.getTeamColor() == ChessGame.TeamColor.BLACK){
//            if color at start position, and the double position is not blocked, add piece move to collection
                row = row - 1;
                ChessPosition doublePosition = new ChessPosition(row, column);
                if (permStart.getRow() == 7){
                    if(chessBoard.getPiece(doublePosition) == null){
                        ChessMove doubleMove = new ChessMove(permStart, doublePosition, null);
                        chessMoveCollection.add(doubleMove);
                    }
                }
            }

        }
//  check to see if there is any piece there. cannot move forward whether black or white
//        check right diagonal
        row = permStart.getRow() + LorR;
        column = permStart.getColumn() + 1;

        if(row > 8 | column > 8){
            return;
        }
        if(row < 1 || column < 1){
            return;
        }

        ChessPosition diagonalPosition = new ChessPosition(row, column);
        if (chessBoard.getPiece(diagonalPosition) != null) {
//            if piece is enemy color then they can move there
            if(chessBoard.getPiece(diagonalPosition).getTeamColor() != chessPiece.getTeamColor()) {
//                can capture piece
                ChessMove tmpMove = new ChessMove(permStart, diagonalPosition, null);
                chessMoveCollection.add(tmpMove);
            }
        }
//        check left diagonal
        row = permStart.getRow() + LorR;
        column = permStart.getColumn() -1;
        if(row > 8 | column > 8){
            return;
        }
        if(row < 1 || column < 1){
            return;
        }

        ChessPosition diagonalPosition2 = new ChessPosition(row, column);
        if (chessBoard.getPiece(diagonalPosition2) != null) {
//            if piece is enemy color then they can move there
            if(chessBoard.getPiece(diagonalPosition2).getTeamColor() != chessPiece.getTeamColor()) {
                ChessMove tmpMove = new ChessMove(permStart, diagonalPosition2, null);
                chessMoveCollection.add(tmpMove);
            }
        }

    }

}
