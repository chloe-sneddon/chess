package chess;

public class DefaultBoard {
    private ChessBoard newBoard;
    DefaultBoard(){
        newBoard = new ChessBoard();
    }
    public ChessBoard SetDefaultBoard(){

//      Pawn Location
//          black
//            (all row 7)
        for (int i = 1; i < 9; i++){
            setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.PAWN, 7,i);
        }
//          white
//            (all row 2)
        for (int i = 1; i < 9; i++){
            setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.PAWN, 2,i);
        }

//      King Location
//          black
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KING, 8,5);
//            (row=8,col=5)
//          white
//            (row=1,col=5)
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KING, 1,5);

//      Queen Location
//          black
//            (row=8,col=4)
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.QUEEN, 8,4);

//          white
//            (row=1,col=4)
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.QUEEN, 1,4);

//      Bishop location
//          black
//              (row=8,col=3) (row=8,col=6)
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP, 8,3);
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.BISHOP, 8,6);
//          white
//              (row=1,col=3) (row=1,col=6)
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP, 1,3);
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.BISHOP, 1,6);


//      Knight Location
//          black
//              (row=8,col=2) (row=8,col=7)
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT, 8,2);
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.KNIGHT, 8,7);
//          white
//              (row=1,col=2) (row=1,col=7)
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT, 1,2);
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.KNIGHT, 1,7);

//      Rook Location
//          black
//              (row=8,col1) (row=8,col=8)
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK, 8,1);
        setPiece(ChessGame.TeamColor.BLACK,ChessPiece.PieceType.ROOK, 8,8);
//          white
//              (row=1,col=1) (row=1,col=2)
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK, 1,1);
        setPiece(ChessGame.TeamColor.WHITE,ChessPiece.PieceType.ROOK, 1,8);


        return newBoard;
    }
    public void setPiece(ChessGame.TeamColor teamColor, ChessPiece.PieceType pieceType,int row, int col){
        ChessPiece newPiece = new ChessPiece(teamColor, pieceType);
        ChessPosition newPosition = new ChessPosition(row,col);
        this.newBoard.addPiece(newPosition,newPiece);
    }
}
