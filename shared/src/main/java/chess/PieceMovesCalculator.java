package chess;

import java.util.Collection;


public class PieceMovesCalculator {
//    private ChessPiece piece;
    private ChessBoard board;
    private ChessPosition position;
    private Collection <ChessMove> tmp;

    public PieceMovesCalculator(ChessBoard board, ChessPosition position) {
        this.board = board;
        this.position = position;

        ChessPiece myPiece = board.getPiece(position);

//      TODO: Use override and hashcode to write the following if statments to determine piece type
//      TODO: write a function that checks an if the board copy is set up this way the king will be in check
//           - note there will be a function isInCheck() which returns true if the king is in check
//           - use this function to check each movement before adding it to the collection

//        if(myPiece.getPieceType() == PieceType.BISHOP){
////            TODO: add another check to ensure the piecetype is bishop, throw error if it is not
//            BishopMovesCalculator movesCalculation = new BishopMovesCalculator(board, position);
//        }

    }

    //BISHOP
    public static class BishopMovesCalculator {
//        private ChessBoard board;
//        private ChessPosition position;
//        private Collection <ChessMove> tmp;
//
//        public BishopMovesCalculator(ChessBoard board, ChessPosition position) {
//            this.board = board;
//            this.position = position;
//
//        }
    }

    //KING
    public static class KingMovesCalculator {
    }

    //QUEEN
    public static class QueenMovesCalculator {
    }

    //Knight
    public static class KnightMovesCalculator {
    }

    //ROOK
    public static class RookMovesCalculator {
    }

    //PAWN
    public static class PawnMovesCalculator {
    }
}