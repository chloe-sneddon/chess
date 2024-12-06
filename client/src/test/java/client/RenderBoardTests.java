package client;

import chess.ChessPosition;
import org.junit.jupiter.api.*;
import ui.HighlightBoard;

public class RenderBoardTests {
    public HighlightBoard visual = new HighlightBoard();

    @Test
    public void highlightBoard(){
        ChessPosition position = new ChessPosition(2,4);
        visual.runHighlight("WHITE", position);
    }
}
