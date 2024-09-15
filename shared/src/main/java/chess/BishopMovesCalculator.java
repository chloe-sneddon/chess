package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator implements PieceMovesCalculator {
    private Collection <ChessMove> chessMoveCollection;
    private ChessPosition permStart;
    private ChessBoard chessBoard;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
//       TODO: implement calculations

        System.out.print("---Bishop Calculator()---");
//        TODO: First do a general generic calculation
//        TODO: if there is another piece in the way, then terminate that line
//        Calculate positive, positive diagonals
        permStart = position;
        chessBoard = board;
        chessMoveCollection = new ArrayList<>();

        doublePositiveDiagonal();

        return chessMoveCollection;
    }
//    private doublePositiveDiagonal(){
    //    for/until cannot move forward:
    //       tmp position = first then previous position
    //       row + 1 , col + 1
    //       new ChessPosition newChessPosition= row,col
    //       new ChessMove newChessMove = start,newChessPosition
    //       Collection <ChessMove> pushback newChessMove
//    }
    private void doublePositiveDiagonal(){
//        update universal variable chessMoveCollection
        //    for/until cannot move forward:
        System.out.println("\n---double positive diagonal()---");
        ChessPosition tmpPosition = permStart;
        for (int i = 0; i < 8; i++){
            //       tmp position = first then next position after iter
            //       row + 1 , col + 1
            int row = tmpPosition.getRow() + 1;
            int column = tmpPosition.getColumn() + 1;
            if (row > 8 | column > 8){
                break;
            }
            //       new ChessPosition newChessPosition= row,col
            ChessPosition newPosition = new ChessPosition(row, column);
            //       new ChessMove newChessMove = start,newChessPosition
            ChessMove tmpMove = new ChessMove(permStart, newPosition, ChessPiece.PieceType.BISHOP);
            //       Collection <ChessMove> pushback newChessMove
            chessMoveCollection.add(tmpMove);
            //       update tmpPosition
            tmpPosition = newPosition;
            System.out.println(tmpPosition.toString());
            System.out.print("chessMoveCollection: " + chessMoveCollection.toString());
        }
    }
//    private void calcDiagonals(int rowInc,int columnInc){
//
//    }
}
