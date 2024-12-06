package ui;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import chess.*;

import static ui.EscapeSequences.*;


public class RenderBoard {
    // Board dimensions.
    public static final int BOARD_WIDTH = 8;
    public static final int SQUARE_SIZE_IN_PADDED_CHARS = 7;
    public static ChessBoard board;
    public static String playerColor;
    public static ChessGame game;


    public void run(String playerCol) {
//        note: can erase after phase 5
        game = new ChessGame();
        board = game.getBoard();

        playerColor = playerCol;

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

        if (playerColor.equals("BLACK")){
            colHeaders = new String[]{EMPTY, " H ", " G ", " F "," E "," D "," C "," B "," A "};
        }

        out.print(SET_TEXT_COLOR_BLUE);
        for (int i = 0; i <= BOARD_WIDTH; ++i) {
            drawSquare(out,"HEADER",colHeaders[i]);
        }
    }

    public static void drawBoard(PrintStream out) {
        String[] rowHeaders = { " 8 ", " 7 ", " 6 "," 5 "," 4 "," 3 "," 2 "," 1 "};
        if (playerColor.equals("BLACK")){
            rowHeaders = new String[]{" 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "};
        }
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
        if(Objects.equals(bgColor, "WHITE")){
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
            if (Objects.equals(startColor, "WHITE")){
                startColor = "BLACK";
            }
            else{
                startColor = "WHITE";
            }
        }
    }

    public static String getPieceString (PrintStream out,int rowPrint, int colPrint){
        int row = rowPrint;
        int col = colPrint;

        if (playerColor.equals("BLACK")){
            col = 7 - colPrint;
        }
        else{
            row = 7 - rowPrint;
        }

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

    public void updateBoard(ChessBoard chessBoard){
        game.setBoard(chessBoard);
    }
}
