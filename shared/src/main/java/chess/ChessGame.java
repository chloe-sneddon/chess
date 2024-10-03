package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


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

    public TeamColor getTeamTurn() {
        return teamTurn;
    }

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
        if (board.getPiece(startPosition) == null){
//            no piece in this location so no moves
            return null;
        }
//      go through each of the moves returned by pieceMove and implement the move on the board (MAKE SURE TO CHANGE IT BACK!!!)
//      test for isInCheck()
        TeamColor pieceColor = board.getPiece(startPosition).getTeamColor();
        ChessPiece movePiece = board.getPiece(startPosition);
        ChessBoard permBoard = new ChessBoard(board);
        Collection <ChessMove> moves = board.getPiece(startPosition).pieceMoves(board,startPosition);
        Collection <ChessMove> filteredMoves = new ArrayList<>();

        for (ChessMove move : moves) {
                board.movePiece(move,movePiece);
                if(!isInCheck(pieceColor)){
                    filteredMoves.add(move);
                }
                board.setBoard(permBoard);
        }

//        if(filteredMoves.isEmpty()){
//            filteredMoves = null;
//        }

        return filteredMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {

        ChessPiece pieceToMove = board.getPiece(move.getStartPosition());

        if(pieceToMove == null){
            invalidMoveException = new InvalidMoveException("No piece in this location");
            throw invalidMoveException;
        }

        if(teamTurn != pieceToMove.getTeamColor()){
            invalidMoveException = new InvalidMoveException("Not this color's turn");
            throw invalidMoveException;
        }

        Collection <ChessMove> valMoves = validMoves(move.getStartPosition());
        boolean exists = false;
        for(ChessMove compareVal: valMoves){
            if(move.equals(compareVal)){
                exists = true;
                break;
            }
        }
//      if move is not found in valid move list throw error
        if(!exists){
            invalidMoveException = new InvalidMoveException("Not a valid move");
            throw invalidMoveException;
        }

        //        Pawn promotion is handled here!
        if(move.getPromotionPiece() != null){
            pieceToMove.executePromotion(move.getPromotionPiece());
        }

//      finally move the d*** piece
        board.movePiece(move,pieceToMove);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //        get other color
        TeamColor opposingTeam;
        if (teamColor == TeamColor.WHITE){
            opposingTeam = TeamColor.BLACK;
        }
        else{
            opposingTeam = TeamColor.WHITE;
        }

//        check all possible piece moves from the other team,
        Collection <ChessPosition> startPositions = board.getTeamPositions(opposingTeam);

        for (ChessPosition startPosition : startPositions){
//            calculate all moves and if they can capture a piece, and it's a king, then yes in check
            Collection <ChessMove> moves = board.getPiece(startPosition).pieceMoves(board,startPosition);
            for (ChessMove move:moves){
                if(move.getCapturePiece() == ChessPiece.PieceType.KING){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {

        if(!isInCheck(teamColor)){
            return false;
        }
        else{
//          check all possible piece moves from the team,
            Collection <ChessPosition> startPositions = board.getTeamPositions(teamColor);
            for (ChessPosition startPosition : startPositions){
//            if the king is no longer in check then return false
                Collection <ChessMove> moves = board.getPiece(startPosition).pieceMoves(board,startPosition);
                for (ChessMove move:moves){
//                    TODO: fix this so that it creates a copy board and makes the fake move also fix in is in check function
//                    ChessGame testGame = new ChessGame();
                    if(!isInCheck(teamColor)){
                        return false;
                    }
                }
            }
            return true;
        }

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
//        TODO: king is not in immediate danger.
        Collection <ChessPosition> startPositions = board.getTeamPositions(teamColor);
        Collection <ChessMove> allMoves = new ArrayList<>();
        for(ChessPosition startPosition:startPositions){
            allMoves.addAll(validMoves(startPosition));
        }
        if(allMoves.isEmpty()){
            return true;
        }
        else{
            return false;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) && Objects.equals(invalidMoveException, chessGame.invalidMoveException) && teamTurn == chessGame.teamTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, invalidMoveException, teamTurn);
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "board=" + board +
                ", invalidMoveException=" + invalidMoveException +
                ", teamTurn=" + teamTurn +
                '}';
    }
}
