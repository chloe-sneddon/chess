package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator implements PieceMovesCalculator {
    private Collection<ChessMove> chessMoveCollection;
    private ChessPosition permStart;
    private ChessBoard chessBoard;
    private ChessPiece chessPiece;

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        permStart = position;
        chessBoard = board;
        chessMoveCollection = new ArrayList<>();
        chessPiece = board.getPiece(position);

//      positive row and col
        calcMove(1, 1);
//      neg row and col
        calcMove(-1, -1);
//      pos row neg col
        calcMove(1, -1);
//      neg row pos col
        calcMove(-1, 1);

        return chessMoveCollection;
    }

    private void calcMove(int rowInc, int columnInc) {

        ChessPosition tmpPosition = permStart;

        for (int i = 0; i < 8; i++) {
            int row = tmpPosition.getRow() + rowInc;
            int column = tmpPosition.getColumn() + columnInc;
            if (row > 8 | column > 8) {
                break;
            }
            if(row < 1 | column < 1) {
                break;
            }
            ChessPosition newPosition = new ChessPosition(row, column);
//          check the board at newPosition
            if (chessBoard.getPiece(newPosition) != null) {
//              piece is blocked by self
                if (chessPiece.getTeamColor() == chessBoard.getPiece(newPosition).getTeamColor()) {
                    break;
                }
    //          if there is a piece there of opposite color
                else if (chessPiece.getTeamColor() != chessBoard.getPiece(newPosition).getTeamColor()) {
                    ChessMove tmpMove = new ChessMove(permStart, newPosition, null);
                    chessMoveCollection.add(tmpMove);
                    tmpPosition = newPosition;
                    break;
                }
            }
            ChessMove tmpMove = new ChessMove(permStart, newPosition, null);
            chessMoveCollection.add(tmpMove);
            tmpPosition = newPosition;

        }
    }
}
