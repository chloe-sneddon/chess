package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private ChessBoard board;
    private InvalidMoveException invalidMoveException;
    private TeamColor teamTurn;
//    private

    public ChessGame() {

    }
//    to decouple, implement addPiece and removePiece here
//    https://docs.google.com/presentation/d/1dncxSAgnIqjV9RNzGR94EWVltJiCApqC3EvNPqz97-E/edit#slide=id.g286d6837b19_1_98
//    use this slide (27) for why
    

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
//        TODO: check if friendly king is put in check in any of the moves and remove it from the moves array
        Collection <ChessMove> moves = new ArrayList<>();
        moves = board.getPiece(startPosition).pieceMoves(board,startPosition);
        return moves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
//        Pawn promotion is handled here!
//        try{
    //        if move is in validMoves (call it with move.getStartPosition())
//        and it doesnt leave the team’s king in danger
//        and it’s  the corresponding team's turn.
    //        then remove that piece from start position and place on end position
    //        note, if there is a piece that was there previously, do we need to keep track of it as being captured?
//        }
//        else throws error
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
//        check all possible piece moves from the other team,
//        if any of them can capture king, king is in check
    //        if king is in check, call isInCheckmate(teamColor);
//              if returns true then isInCheck is false
//              if returns false then isInCheck is true
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
//        this method is only called when the king is in check
//        check all possible teamColor moves and see if king can escape
//        or another piece can be placed in path
//        if there is no move possible, then return true
//        else return false
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
//      check all possible moves for teamColor if is empty, then return false
//      (this would be valid moves because it takes into account moving into check)
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
       this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
