package chess;

import java.util.ArrayList;
import java.util.Collection;

public class MovesCalculator {

    public Collection<ChessMove> calcMoves(int rowInc, int colInc, ChessPosition startPosition,ChessBoard board, ChessPiece piece){
//        calculate possible move
        Collection<ChessMove> possibleMoves = new ArrayList<>();
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
//        ChessPosition tmpPosition = new ChessPosition(row,col);
        for(int i  = 0; i < 8; i++){
            row = row + rowInc;
            col = col + colInc;
            ChessPosition newPosition = new ChessPosition(row,col);
            //        check to see if it's out of bounds
            if (row < 1 | col < 1){
                return possibleMoves;
            }
            if(row > 8 | col > 8){
                return possibleMoves;
            }
            //        check to see if there is a piece in the way
            if(board.getPiece(newPosition) != null){
//                if piece is enemy piece can capture but loop breaks
                if(board.getPiece(newPosition).getTeamColor() != piece.getTeamColor()){
                    ChessPiece.PieceType capturePiece = board.getPiece(newPosition).getPieceType();
                    ChessMove newMove = new ChessMove(startPosition,newPosition,null,capturePiece);
                    possibleMoves.add(newMove);
                    break;
                }
                else{
                    break;
                }
            }
//            still a valid move
            ChessMove newMove = new ChessMove(startPosition,newPosition,null);
            possibleMoves.add(newMove);
            //        if there is a piece in the way mark it as possible capture and terminate line
        }
        return possibleMoves;
    }
}
