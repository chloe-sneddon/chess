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
//    private InvalidMoveException invalidMoveException;
    private TeamColor teamTurn;
//    private

    public ChessGame() {
        board = new ChessBoard();
        board.resetBoard();
        teamTurn = TeamColor.WHITE;
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
        ChessBoard permBoard = new ChessBoard();
        permBoard.setBoard(board);
        Collection <ChessMove> moves = board.getPiece(startPosition).pieceMoves(board,startPosition);
        Collection <ChessMove> filteredMoves = new ArrayList<>();

        for (ChessMove move : moves) {
                board.movePiece(move,movePiece);
                if(!isInCheck(pieceColor)){
                    filteredMoves.add(move);
                }
                board.setBoard(permBoard);
        }

        return filteredMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(board == null){
            throw new InvalidMoveException("No board found");
        }

        ChessPiece pieceToMove = board.getPiece(move.getStartPosition());

        if(pieceToMove == null){
            throw new InvalidMoveException("No piece in this location");
        }

        if(teamTurn != pieceToMove.getTeamColor()){
            throw new InvalidMoveException("Not this color's turn");
        }

        Collection <ChessMove> valMoves = validMoves(move.getStartPosition());

        boolean exists = valMoves.contains(move);

//      if move is not found in valid move list throw error
        if(!exists){
            throw new InvalidMoveException("Not a valid move");
        }

//        Pawn promotion is handled here!
        if(move.getPromotionPiece() != null){
            pieceToMove.executePromotion(move.getPromotionPiece());
        }

//      finally move the d*** piece
        board.movePiece(move,pieceToMove);

//        change turns
        if(teamTurn == TeamColor.WHITE){
            teamTurn = TeamColor.BLACK;
        }
        else{
            teamTurn = TeamColor.WHITE;
        }

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
            Collection <ChessMove> moves = getAllMoves(teamColor);

            if(moves.isEmpty()){
                return true;
            }

            else{
                return false;
            }
        }

    }

    public Collection <ChessMove> getAllMoves(TeamColor teamColor){
        Collection <ChessPosition> startPositions = board.getTeamPositions(teamColor);
        Collection <ChessMove> moves = new ArrayList<>();

        for (ChessPosition startPosition : startPositions){
            Collection <ChessMove> tmpMoves = validMoves(startPosition);
            moves.addAll(tmpMoves);
        }
        return moves;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
//        basically checkmate but the king isn't in check
        if (!isInCheck(teamColor)){
            Collection <ChessMove> moves = getAllMoves(teamColor);

            if(moves.isEmpty()){
                return true;
            }

            else{
                return false;
            }
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) && teamTurn == chessGame.teamTurn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, teamTurn);
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "board=" + board +
                ", teamTurn=" + teamTurn +
                '}';
    }
}
