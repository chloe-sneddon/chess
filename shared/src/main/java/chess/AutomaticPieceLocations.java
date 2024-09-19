package chess;

import java.util.ArrayList;
import java.util.Collection;

//this class is to set ChessPiece and ChessPosition to then be set on the board
public class AutomaticPieceLocations {
//    array of all white pieces
//    array of all black pieces
    private final ChessBoard board;
    AutomaticPieceLocations(ChessBoard board) {
        this.board = board;
//        call each of the methods
    }
    private void bishopLocation(){
//        white
        ChessPosition b1pos = new ChessPosition(1,3);
        ChessPosition b2pos = new ChessPosition(1,6);
//        black
        ChessPosition b3pos = new ChessPosition(8,3);
        ChessPosition b4pos = new ChessPosition(8,6);

        ChessPiece b1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPiece b2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        ChessPiece b3 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        ChessPiece b4 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);

        board.addPiece(b1pos,b1);
        board.addPiece(b2pos,b2);
        board.addPiece(b3pos,b3);
        board.addPiece(b4pos,b4);
    }
    private void rookLocation(){
//        white
        ChessPosition r1pos = new ChessPosition(1,1);
        ChessPosition r2pos = new ChessPosition(1,8);
//        black
        ChessPosition r3pos = new ChessPosition(8,1);
        ChessPosition r4pos = new ChessPosition(8,8);

        ChessPiece r1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPiece r2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        ChessPiece r3 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        ChessPiece r4 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);

        board.addPiece(r1pos,r1);
        board.addPiece(r2pos,r2);
        board.addPiece(r3pos,r3);
        board.addPiece(r4pos,r4);
    }
    private void knightLocation() {
//        white
        ChessPosition k1pos = new ChessPosition(1, 2);
        ChessPosition k2pos = new ChessPosition(1, 7);
//        black
        ChessPosition k3pos = new ChessPosition(8, 2);
        ChessPosition k4pos = new ChessPosition(8, 7);

        ChessPiece k1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPiece k2 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        ChessPiece k3 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        ChessPiece k4 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);

        board.addPiece(k1pos, k1);
        board.addPiece(k2pos, k2);
        board.addPiece(k3pos, k3);
        board.addPiece(k4pos, k4);
    }
    private void kingLocation() {
//        white
        ChessPosition ki1pos = new ChessPosition(1, 5);
//        black
        ChessPosition ki2pos = new ChessPosition(8, 5);

        ChessPiece ki1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        ChessPiece ki2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);

        board.addPiece(ki1pos, ki1);
        board.addPiece(ki2pos, ki2);
    }
    private void queenLocation() {
//        white
        ChessPosition q1pos = new ChessPosition(1, 4);
//        black
        ChessPosition q2pos = new ChessPosition(8, 4);

        ChessPiece q1 = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        ChessPiece q2 = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);

        board.addPiece(q1pos, q1);
        board.addPiece(q2pos, q2);
    }
    private void pawnLocation() {
//        white first
        for (int i = 1; i < 9; i++){
            ChessPosition pawnPosition = new ChessPosition(2, i);
            ChessPiece pawn = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            board.addPiece(pawnPosition, pawn);
        }
//        next black
        for (int i = 1; i < 9; i++){
            ChessPosition pawnPosition = new ChessPosition(7, i);
            ChessPiece pawn = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            board.addPiece(pawnPosition, pawn);
        }

    }
    public void resetBoard(){
        bishopLocation();
        rookLocation();
        knightLocation();
        kingLocation();
        queenLocation();
        pawnLocation();
        return;
    }
}
