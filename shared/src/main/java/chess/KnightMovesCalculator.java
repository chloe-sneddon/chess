package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMovesCalculator {
    private Collection<ChessMove> possibleMoves;
    private ChessPosition startPosition;
    private ChessBoard board;
    private ChessPiece piece;

    KnightMovesCalculator(){}
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPiece piece, ChessPosition startPosition){
        this.startPosition = startPosition;
        this.board = board;
        this.piece = piece;
        possibleMoves = new ArrayList<>();

        calcMoves(1,2);
        calcMoves(-1,2);
        calcMoves(2,-1);
        calcMoves(2,1);

        calcMoves(1,-2);
        calcMoves(-1,-2);
        calcMoves(-2,-1);
        calcMoves(-2,1);



        return possibleMoves;
    }
    private void calcMoves(int rowInc, int colInc){
//        calculate possible move
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
//        ChessPosition tmpPosition = new ChessPosition(row,col);
        row = row + rowInc;
        col = col + colInc;
        //        check to see if it's out of bounds
        if (row < 1 | col < 1){
            return;
        }
        if(row > 8 | col > 8){
            return;
        }
        ChessPosition newPosition = new ChessPosition(row,col);
        //        check to see if there is a piece in the way
        if(board.getPiece(newPosition) != null){
//                if piece is enemy piece can capture
            if(board.getPiece(newPosition).getTeamColor() != piece.getTeamColor()){
                ChessMove newMove = new ChessMove(startPosition,newPosition,null,board.getPiece(newPosition).getPieceType());
                possibleMoves.add(newMove);
            }
        }
        else {
            ChessMove newMove = new ChessMove(startPosition, newPosition, null);
            possibleMoves.add(newMove);
        }
    }
}