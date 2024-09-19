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
//            if white reaches other side add promotion options
            if((chessPiece.getTeamColor() == ChessGame.TeamColor.WHITE) & (newPosition.getRow() == 8)){
                promotionMoves(newPosition);
            }
//            if black reaches other side, add promotion options
            else if ((chessPiece.getTeamColor() == ChessGame.TeamColor.BLACK)& (newPosition.getRow() == 1)) {
                promotionMoves(newPosition);
            }
//            this is still a valid move
            else{
                ChessMove tmpMove = new ChessMove(permStart, newPosition, null);
                chessMoveCollection.add(tmpMove);
            }

//          if () color is white and in start (row 2) then check for double
            if((chessPiece.getTeamColor() == ChessGame.TeamColor.WHITE)&(permStart.getRow() == 2)){
//            if color at start position, and the double position is not blocked, add piece move to collection
                row = row + 1;
                ChessPosition doublePosition = new ChessPosition(row, column);
                if(chessBoard.getPiece(doublePosition) == null){
                    ChessMove doubleMove = new ChessMove(permStart, doublePosition, null);
                    chessMoveCollection.add(doubleMove);
                }
            }
//            if () color is black and in start (row 7) then check for double
            if((chessPiece.getTeamColor() == ChessGame.TeamColor.BLACK)&(permStart.getRow() == 7)){
//            if color at start position, and the double position is not blocked, add piece move to collection
                row = row - 1;
                ChessPosition doublePosition = new ChessPosition(row, column);
                if(chessBoard.getPiece(doublePosition) == null){
                    ChessMove doubleMove = new ChessMove(permStart, doublePosition, null);
                    chessMoveCollection.add(doubleMove);
                }
            }

        }

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
//              if new row is 8 for white or 0 for black then add promotion possibilities
                if((chessPiece.getTeamColor() == ChessGame.TeamColor.WHITE) & (diagonalPosition.getRow() == 8)){
                    promotionMoves(diagonalPosition);
                }
//            if black reaches other side, add promotion options
                else if ((chessPiece.getTeamColor() == ChessGame.TeamColor.BLACK)& (diagonalPosition.getRow() == 1)) {
                    promotionMoves(diagonalPosition);
                }
                else{
                ChessMove tmpMove = new ChessMove(permStart, diagonalPosition, null);
                chessMoveCollection.add(tmpMove);
                }
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
//           if piece is enemy color then they can move there
            if(chessBoard.getPiece(diagonalPosition2).getTeamColor() != chessPiece.getTeamColor()) {
//                can capture piece
//              if new row is 8 for white or 0 for black then add promotion possibilities
                if((chessPiece.getTeamColor() == ChessGame.TeamColor.WHITE) & (diagonalPosition2.getRow() == 8)){
                    promotionMoves(diagonalPosition2);
                }
//            if black reaches other side, add promotion options
                else if ((chessPiece.getTeamColor() == ChessGame.TeamColor.BLACK)& (diagonalPosition2.getRow() == 1)) {
                    promotionMoves(diagonalPosition2);
                }
                else{
                    ChessMove tmpMove = new ChessMove(permStart, diagonalPosition2, null);
                    chessMoveCollection.add(tmpMove);
                }
            }
        }

    }
    private void promotionMoves(ChessPosition promoPosition){
//        move promo queen
        ChessMove qMove = new ChessMove(permStart, promoPosition, ChessPiece.PieceType.QUEEN);
        chessMoveCollection.add(qMove);
//        move promo rook
        ChessMove rMove = new ChessMove(permStart, promoPosition, ChessPiece.PieceType.ROOK);
        chessMoveCollection.add(rMove);
//        move promo bishop
        ChessMove bMove = new ChessMove(permStart, promoPosition, ChessPiece.PieceType.BISHOP);
        chessMoveCollection.add(bMove);
//        move promo knight
        ChessMove kMove = new ChessMove(permStart, promoPosition, ChessPiece.PieceType.KNIGHT);
        chessMoveCollection.add(kMove);

    }

}
