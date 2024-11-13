package ui;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import chess.ChessBoard;

import static ui.EscapeSequences.*;


public class RenderBoard {
    // Board dimensions.
    private static final int BOARD_WIDTH = 2;
    private static final int SQUARE_SIZE_IN_PADDED_CHARS = 3;
    private static final int LINE_WIDTH_IN_PADDED_CHARS = 1;
    ChessBoard board = new ChessBoard();


    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawBoard(out);
//        drawHeaders(out);

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    public static void drawHeaders(PrintStream out){
        String[] colHeaders = { "A", "B", "C","D","E","F","G","H"};
        for (int boardCol = 0; boardCol < BOARD_WIDTH; ++boardCol) {
            drawHeader(out, colHeaders[boardCol]);
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
        drawHeaders(out);
        String [] rowHeaders = {"1","2","3","4","5","6","7","8"};
        for (int boardRow = 0; boardRow < BOARD_WIDTH; ++boardRow) {
            drawHeader(out, rowHeaders[boardRow]);
            out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
            drawRow(out);
        }
    }
    private static void drawRow(PrintStream out) {

        for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_PADDED_CHARS; ++squareRow) {

            if (squareRow/2 != 1){
                out.print(SET_TEXT_COLOR_WHITE+SET_BG_COLOR_WHITE);
            }
            else{
                out.print(SET_TEXT_COLOR_BLACK+SET_BG_COLOR_BLACK);
            }

            for (int boardCol = 0; boardCol < BOARD_WIDTH; ++boardCol) {
                if (boardCol/2 != 1){
                    out.print(SET_TEXT_COLOR_WHITE+SET_BG_COLOR_WHITE);
                }
                else{
                    out.print(SET_TEXT_COLOR_BLACK+SET_BG_COLOR_BLACK);
                }
                if (squareRow == SQUARE_SIZE_IN_PADDED_CHARS / 2) {
                    int prefixLength = SQUARE_SIZE_IN_PADDED_CHARS / 2;
                    int suffixLength = SQUARE_SIZE_IN_PADDED_CHARS - prefixLength - 1;

                    out.print(EMPTY.repeat(prefixLength));
//                    printPlayer(out, rand.nextBoolean() ? X : O);
                    out.print(EMPTY.repeat(suffixLength));
                }
                else {
                    out.print(EMPTY.repeat(SQUARE_SIZE_IN_PADDED_CHARS));
                }

                if (boardCol < BOARD_WIDTH - 1) {
                    // Draw vertical column separator.
                    out.print(EMPTY.repeat(LINE_WIDTH_IN_PADDED_CHARS));
                }
                out.print(SET_TEXT_COLOR_BLACK+SET_BG_COLOR_BLACK);
//                setBlack(out);
            }

            out.println();
        }
    }
    public void printPiece(){}
}
