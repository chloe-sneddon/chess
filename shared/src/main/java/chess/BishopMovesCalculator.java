package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator implements PieceMovesCalculator {
    private Collection<ChessMove> chessMoveCollection;
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

//        doublePositiveDiagonal();
//      positive row and col
        calcDiagonals(1, 1);
//      neg row and col
        calcDiagonals(-1, -1);
//      pos row neg col
        calcDiagonals(1, -1);
//      neg row pos col
        calcDiagonals(-1, 1);
        return chessMoveCollection;
    }

    private void calcDiagonals(int rowInc, int columnInc) {
        System.out.println("\n---double positive diagonal()---");
        ChessPosition tmpPosition = permStart;
        for (int i = 0; i < 8; i++) {
            //       tmp position = first then next position after iter
            //       row + 1 , col + 1
            int row = tmpPosition.getRow() + rowInc;
            int column = tmpPosition.getColumn() + columnInc;
            if (row > 8 | column > 8) {
                break;
            }
            if(row < 1 | column < 1) {
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
}
