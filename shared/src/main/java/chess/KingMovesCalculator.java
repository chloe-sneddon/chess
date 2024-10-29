package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMovesCalculator extends MovesCalculator {
    private Collection<ChessMove> possibleMoves;
    private ChessPosition startPosition;
    private ChessBoard board;
    private ChessPiece piece;

    KingMovesCalculator(){}
    public Collection<ChessMove> calculateMoves(ChessBoard board, ChessPiece piece, ChessPosition startPosition){
        this.startPosition = startPosition;
        this.board = board;
        this.piece = piece;
        possibleMoves = new ArrayList<>();

//        Diagonals

        calcMoves(1,1);
        calcMoves(-1,1);
        calcMoves(1,-1);
        calcMoves(-1,-1);
//        possibleMoves.addAll(calcMoves(1,1,startPosition,board,piece));
//        possibleMoves.addAll(calcMoves(-1,1, startPosition,board,piece));
//        possibleMoves.addAll(calcMoves(1,-1, startPosition,board,piece));
//        possibleMoves.addAll(calcMoves(-1,-1, startPosition,board,piece));

//        Straights

        calcMoves(1,0);
        calcMoves(-1,0);
        calcMoves(0,-1);
        calcMoves(0,1);
//        possibleMoves.addAll(calcMoves(1,0,startPosition,board,piece));
//        possibleMoves.addAll(calcMoves(-1,0, startPosition,board,piece));
//        possibleMoves.addAll(calcMoves(0,-1, startPosition,board,piece));
//        possibleMoves.addAll(calcMoves(0,1, startPosition,board,piece));

        return possibleMoves;
    }
    private void calcMoves(int rowInc, int colInc){
//        calculate possible move
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
//        ChessPosition tmpPosition = new ChessPosition(row,col);
            row = row + rowInc;
            col = col + colInc;
            ChessPosition newPosition = new ChessPosition(row,col);
            //        check to see if it's out of bounds
            if (row < 1 | col < 1){
                return;
            }
            if(row > 8 | col > 8){
                return;
            }
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