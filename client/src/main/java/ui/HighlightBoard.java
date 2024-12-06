package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import static ui.EscapeSequences.*;
import static ui.EscapeSequences.SET_BG_COLOR_BLACK;

public class HighlightBoard extends RenderBoard{

    private static final int BOARD_WIDTH = 8;
    private static final int SQUARE_SIZE_IN_PADDED_CHARS = 7;
    private static ChessBoard board;
//    private static String playerColor;
    private static ChessGame game;
    private static HashMap<Integer, ArrayList<Integer>> row_Col_Map;

    public void runHighlight(String playerCol, ChessPosition position){
        run(playerCol);
        game = new ChessGame();
        board = game.getBoard();
//        updatePlayerColor(playerCol);
        playerColor = playerCol;

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n");

        highlightBoard(out, position);
        out.print(SET_BG_COLOR_BLACK);
        out.print("\n");
        drawHeaders(out);

        out.print(SET_BG_COLOR_BLACK);
        out.print("\n\n\n");
        out.print(RESET_BG_COLOR + RESET_TEXT_COLOR);
    }

    public static void highlightBoard(PrintStream out, ChessPosition position){

        Collection<ChessMove> valMoves = game.validMoves(position);

        if(valMoves == null){
            drawBoard(out);
            out.print("NO VALID MOVES");
            return;
        }

        row_Col_Map = new HashMap<>();
        ArrayList <Integer> tmp = new ArrayList<>();
        tmp.add(position.getColumn());
        row_Col_Map.put(position.getRow(),tmp);

        for(ChessMove move : valMoves){
            position = move.getEndPosition();
            int row = position.getRow();
            int col = position.getColumn();

            if(row_Col_Map.get(row) != null){
                row_Col_Map.get(row).add(col);
            }
            else{
                ArrayList <Integer> list = new ArrayList<>();
                list.add(col);
                row_Col_Map.put(row,list);
            }
        }
//      print headers
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
            ArrayList<Integer> highlightSquares = row_Col_Map.get(boardRow);
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
                boolean highlight = false;
                String printVal = getPieceString(out,boardRow,boardCol);

                if(highlightSquares != null){
                    for (int square: highlightSquares){
                        if(square == boardCol){
                            highlight = true;
                            break;
                        }
                    }
                }

//              Draw middle square with text
                if (squareColor.equals("WHITE")){
                    drawSquareHI(out, "WHITE",printVal,highlight);
                    squareColor = "BLACK";
                }
                else{
                    drawSquareHI(out, "BLACK",printVal,highlight);
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
    public static void drawSquareHI(PrintStream out, String bgColor, String printVal, boolean highlight){
        out.print(SET_TEXT_BOLD);
        if(Objects.equals(bgColor, "WHITE")){
            if(highlight){
                out.print(SET_TEXT_COLOR_LIGHT_GREY);
            }
            else {
                out.print(SET_BG_COLOR_WHITE);
            }
        }
        else if (bgColor.equals("BLACK")){
            if(highlight){
                out.print(SET_BG_COLOR_DARK_GREY);
            }
            else{
                out.print(SET_BG_COLOR_BLUE);
            }
        }
        else{
            out.print(SET_BG_COLOR_BLACK);
        }
        int padding = (SQUARE_SIZE_IN_PADDED_CHARS - 1) / 2;
        out.print(" ".repeat(padding) + printVal + " ".repeat(padding));

    }
}
