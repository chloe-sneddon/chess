package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoveCalculator {
    private Collection<ChessMove> possibleMoves;
    private ChessPosition startPosition;
    private ChessBoard board;
    private ChessPiece piece;

    PawnMoveCalculator(){}
    public Collection<ChessMove> CalculateMoves(ChessBoard board, ChessPiece piece, ChessPosition startPosition){
        this.startPosition = startPosition;
        this.board = board;
        this.piece = piece;
        possibleMoves = new ArrayList<>();

//        Diagonals
        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            calcForwardMoves(1);
            calcDiagonalMoves(1,1);
            calcDiagonalMoves(1,-1);
        }
        else{
            calcForwardMoves(-1);
            calcDiagonalMoves(-1,1);
            calcDiagonalMoves(-1,-1);
        }

        return possibleMoves;
    }
    private void calcForwardMoves(int rowChange){

//        calculate possible forward move
        int row = startPosition.getRow() + rowChange;
        int col = startPosition.getColumn();

        ChessPosition newPosition = new ChessPosition(row,col);
        //        check to see if it's out of bounds
        if (row < 1 | col < 1){
            return;
        }
        if(row > 8 | col > 8){
            return;
        }
        //        check to see if no piece in way
        if(board.getPiece(newPosition) == null){
//
//            check to see if white or black is in start position
            if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
//                check to see if it can be promoted
                if(newPosition.getRow() == 8){
                    isPromotionLocation(newPosition);
                }
                else{
                    ChessMove bb = new ChessMove(startPosition, newPosition, null);
                    possibleMoves.add(bb);
                }
//                  check to see if it can move forward two (or is at start position)
                if(startPosition.getRow() == 2){
//                    can move two forward
                    ChessPosition jumpPos = new ChessPosition(row+rowChange,col);
                    if (board.getPiece(jumpPos) == null){
                        ChessMove newMove = new ChessMove(startPosition, jumpPos, null);
                        possibleMoves.add(newMove);
                    }
                }
            }
            else{
                if(newPosition.getRow() == 1){
                    isPromotionLocation(newPosition);
                }
                else{
                    ChessMove bb = new ChessMove(startPosition, newPosition, null);
                    possibleMoves.add(bb);
                }
//                  check to see if it can move forward two (or is at start position)
                if(startPosition.getRow() == 7){
//                    can move two forward
                    ChessPosition jumpPos = new ChessPosition(row+rowChange,col);
                    if (board.getPiece(jumpPos) == null){
                        ChessMove tmpMove = new ChessMove(startPosition, jumpPos, null);
                        possibleMoves.add(tmpMove);
                    }
                }
            }

        }

    }
    private void calcDiagonalMoves(int rowChange, int colChange){
//        check diagonal
        int row = startPosition.getRow() + rowChange;
        int col = startPosition.getColumn() + colChange;

        if (row < 1 | col < 1){
            return;
        }
        if(row > 8 | col > 8){
            return;
        }

        ChessPosition tmpPosition = new ChessPosition(row,col);

//      if there's a piece there check to see if enemy color
        if(board.getPiece(tmpPosition) != null){
            if(board.getPiece(tmpPosition).getTeamColor() != piece.getTeamColor()){
//                Can capture!
//                check to see if promotion piece
                if((piece.getTeamColor() == ChessGame.TeamColor.WHITE) & (tmpPosition.getRow() == 8)){
                    isPromotionLocation(tmpPosition);
                }
                else if((piece.getTeamColor() == ChessGame.TeamColor.BLACK) & (tmpPosition.getRow() == 1)){
                    isPromotionLocation(tmpPosition);
                }
                else {
                    ChessMove newMove = new ChessMove(startPosition, tmpPosition, null);
                    possibleMoves.add(newMove);
                }
            }
        }
    }
    private void isPromotionLocation(ChessPosition newPosition){
        ChessMove qMove = new ChessMove(startPosition,newPosition, ChessPiece.PieceType.QUEEN);
        ChessMove rMove = new ChessMove(startPosition,newPosition, ChessPiece.PieceType.ROOK);
        ChessMove kMove = new ChessMove(startPosition,newPosition, ChessPiece.PieceType.KNIGHT);
        ChessMove bMove = new ChessMove(startPosition,newPosition, ChessPiece.PieceType.BISHOP);

        possibleMoves.add(qMove);
        possibleMoves.add(rMove);
        possibleMoves.add(kMove);
        possibleMoves.add(bMove);
    }
}