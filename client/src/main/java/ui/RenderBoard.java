package ui;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;

import static ui.EscapeSequences.*;


public class RenderBoard {
    // Board dimensions.
    private static final int BOARD_WIDTH = 8;
    private static final int SQUARE_SIZE_IN_PADDED_CHARS = 7;
    private static final int LINE_WIDTH_IN_PADDED_CHARS = 1;
    private static ChessBoard board = new ChessBoard();

//    public static void run(String[] args){
    public void run(String playerColor) {
//        note: can erase after phase 5
        board.resetBoard();

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n");

        if (playerColor.equals("BLACK")){

        }
        else{
//            render for white
        }

        drawBoard(out);
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n");
        drawHeaders(out);

        out.print(SET_BG_COLOR_BLACK);
        out.print("\n\n\n");
        out.print(RESET_BG_COLOR + RESET_TEXT_COLOR);
    }

    public static void drawHeaders(PrintStream out){
        String[] colHeaders = { EMPTY, " A ", " B ", " C "," D "," E "," F "," G "," H "};
        out.print(SET_TEXT_COLOR_BLUE);
        for (int i = 0; i <= BOARD_WIDTH; ++i) {
            drawSquare(out,"HEADER",colHeaders[i]);
        }
    }

    private static void drawBoard(PrintStream out) {
        String[] rowHeaders = {" 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "};
//        default is drawing for a white board
        String squareColor = "BLACK";
        var currentBoard = board.getBoard();
//      row between header and board
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n\n");

        for (int boardRow = 0; boardRow < BOARD_WIDTH; ++boardRow) {
//            print a black row between header and board
            out.print(SET_BG_COLOR_BLACK);
            out.print("\n");
//            set left-most row color
            if (squareColor.equals("WHITE")){
                squareColor = "BLACK";
            }
            else{
                squareColor = "WHITE";
            }
//            print vertical padding
            drawSquare(out,"HEADER",EMPTY);
            printVerticalPadding(out,squareColor);
            out.print(SET_BG_COLOR_BLACK + SET_TEXT_COLOR_BLUE);
            out.print("\n");
            drawSquare(out,"HEADER",rowHeaders[boardRow]);

            for (int boardCol = 0; boardCol < BOARD_WIDTH; ++boardCol){
//              get string of ChessPiece
                String printVal = getPieceString(out,boardRow,boardCol);

//              Draw middle square with text
                if (squareColor.equals("WHITE")){
                    drawSquare(out, "WHITE",printVal);
                    squareColor = "BLACK";
                }
                else{
                    drawSquare(out, "BLACK",printVal);
                    squareColor = "WHITE";
                }
            }
            out.print(SET_BG_COLOR_BLACK + "\n");
            drawSquare(out,"HEADER",EMPTY);
            printVerticalPadding(out,squareColor);
        }
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n");
    }

    public static void drawSquare(PrintStream out, String bgColor, String printVal){
        out.print(SET_TEXT_BOLD);
        if(bgColor == "WHITE"){
            out.print(SET_BG_COLOR_WHITE);
        }
        else if (bgColor == "BLACK"){
            out.print(SET_BG_COLOR_BLUE);
        }
        else{
            out.print(SET_BG_COLOR_BLACK);
        }
        int padding = (SQUARE_SIZE_IN_PADDED_CHARS - 1) / 2;
        out.print(" ".repeat(padding) + printVal + " ".repeat(padding));

    }

    public static void printVerticalPadding(PrintStream out, String startColor){
        for (int i = 0; i < BOARD_WIDTH; i++){
            drawSquare(out,startColor,EMPTY);
//            alternate
            if (startColor == "WHITE"){
                startColor = "BLACK";
            }
            else{
                startColor = "WHITE";
            }
        }
    }

    public static String getPieceString (PrintStream out,int rowPrint, int colPrint){
//        no piece there
        int row = 7 - rowPrint;
        int col = colPrint;

        ChessPiece piece = board.getBoard()[row][col];

        if (piece == null){
            return EMPTY;
        }
//        white
        ChessPiece.PieceType type = piece.getPieceType();

        if (piece.getTeamColor()== ChessGame.TeamColor.WHITE){
            out.print(SET_TEXT_COLOR_LIGHT_GREY);
            return switch (type){
                case BISHOP -> WHITE_BISHOP;
                case ROOK -> WHITE_ROOK;
                case KNIGHT -> WHITE_KNIGHT;
                case QUEEN -> WHITE_QUEEN;
                case KING -> WHITE_KING;
                case PAWN -> WHITE_PAWN;
                case null -> EMPTY;
            };
        }
//        black
        else{
            out.print(SET_TEXT_COLOR_DARK_GREY);
            return switch (type){
                case BISHOP -> BLACK_BISHOP;
                case ROOK -> BLACK_ROOK;
                case KNIGHT -> BLACK_KNIGHT;
                case QUEEN -> BLACK_QUEEN;
                case KING -> BLACK_KING;
                case PAWN -> BLACK_PAWN;
                case null -> EMPTY;
            };
        }
    }
}
