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
    private static final int SQUARE_SIZE_IN_PADDED_CHARS = 3;
    private static final int LINE_WIDTH_IN_PADDED_CHARS = 1;
    private static ChessBoard board = new ChessBoard();


    public void run() {
        board.resetBoard();
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n");
        drawHeaders(out);
        String cursorLoc = moveCursorToLocation(5,9);
        out.print(cursorLoc);
        drawBoard(out);

        out.print(SET_BG_COLOR_BLACK);
        out.print("\n\n\n");
        out.print(RESET_BG_COLOR + RESET_TEXT_COLOR);
    }

    public static void drawHeaders(PrintStream out){
        String[] colHeaders = { "A", "B", "C","D","E","F","G","H"};
        String[] rowHeaders = {"1","2","3","4","5","6","7","8"};
        out.print(EMPTY+EMPTY);
        for (int boardCol = 0; boardCol < BOARD_WIDTH; ++boardCol) {
            drawHeader(out, colHeaders[boardCol]);
            out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
        }
        for (int i = 0; i < BOARD_WIDTH; ++i) {
            out.println();
            out.println();
            drawHeader(out, rowHeaders[i]);
            out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
        }

    }
    private static void drawHeader(PrintStream out, String headerText) {
        int prefixLength = SQUARE_SIZE_IN_PADDED_CHARS / 2;
        int suffixLength = SQUARE_SIZE_IN_PADDED_CHARS - prefixLength - 1;

        out.print(EMPTY.repeat(prefixLength));
        printHeaderText(out, headerText);
        out.print(EMPTY.repeat(suffixLength));
    }

    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_BLUE);

        out.print(player);
    }
    private static void drawBoard(PrintStream out) {
//        default is drawing for black board
        String squareColor = "BLACK";
        var currentBoard = board.getBoard();

        for (int boardRow = 0; boardRow < BOARD_WIDTH; ++boardRow) {
            out.print(SET_BG_COLOR_BLACK);
            out.print("\n");
            if (squareColor.equals("WHITE")){
                squareColor = "BLACK";
            }
            else{
                squareColor = "WHITE";
            }
            for (int boardCol = 0; boardCol < BOARD_WIDTH; ++boardCol){

                String printVal = getPieceString(out,currentBoard[boardRow][boardCol]);

                if (squareColor.equals("WHITE")){
                    drawSquare(out, "WHITE",printVal);
                    squareColor = "BLACK";
                }
                else{
                    drawSquare(out, "BLACK",printVal);
                    squareColor = "WHITE";
                }
//                out.print(EMPTY.repeat(SQUARE_SIZE_IN_PADDED_CHARS/2));
            }
            out.print(SET_BG_COLOR_BLACK);
        }

    }
    public static void drawSquare(PrintStream out, String bgColor, String printVal){

        if(bgColor == "WHITE"){
            out.print(SET_BG_COLOR_WHITE);
        }
        else{
            out.print(SET_BG_COLOR_BLUE);
        }
        out.print(printVal);
    }


    public static String getPieceString (PrintStream out,ChessPiece piece){
//        no piece there
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
