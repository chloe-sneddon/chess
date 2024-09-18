package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesCalculator implements PieceMovesCalculator {
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
        calcMove(1, 1);
        calcMove(-1, -1);
        calcMove(1, -1);
        calcMove(-1, 1);
//        t-moves
        calcMove(1, 0);
        calcMove(-1, 0);
        calcMove(0, -1);
        calcMove(0, 1);

        return chessMoveCollection;
    }

    private void calcMove(int rowInc, int columnInc) {

        int newRow = permStart.getRow() + rowInc;
        int newColumn = permStart.getColumn() + columnInc;

            if (newRow > 8 | newColumn > 8) {
                return;
            }
            if(newRow < 1 | newColumn < 1) {
                return;
            }
            ChessPosition newPosition = new ChessPosition(newRow, newColumn);
//          check the board at newPosition
            if (chessBoard.getPiece(newPosition) != null) {
//              piece is blocked by self
                if (chessPiece.getTeamColor() == chessBoard.getPiece(newPosition).getTeamColor()) {
                    return;
                }
//              can capture
                else if (chessPiece.getTeamColor() != chessBoard.getPiece(newPosition).getTeamColor()) {
//                    TODO: King can capture
                    ChessMove tmpMove = new ChessMove(permStart, newPosition, ChessPiece.PieceType.BISHOP);
                    chessMoveCollection.add(tmpMove);
                }
            }
            else{
                ChessMove tmpMove = new ChessMove(permStart, newPosition, ChessPiece.PieceType.BISHOP);
                chessMoveCollection.add(tmpMove);
            }
    }
}
