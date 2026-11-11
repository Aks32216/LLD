package Game_Question_Practice.Chess.Pieces;

import Game_Question_Practice.Chess.Enums.Color;
import Game_Question_Practice.Chess.Enums.PieceType;
import Game_Question_Practice.Chess.Models.Board;
import Game_Question_Practice.Chess.Models.Position;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // Forward one square
        if (colDiff == 0 && rowDiff == direction) {
            return board.getPiece(to) == null;
        }

        // Forward two squares from starting position
        if (colDiff == 0 && rowDiff == 2 * direction && !hasMoved) {
            Position intermediate = new Position(
                    from.getRow() + direction, from.getCol());
            return board.getPiece(intermediate) == null
                    && board.getPiece(to) == null;
        }

        // Diagonal capture (including en passant)
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            Piece target = board.getPiece(to);
            if (target != null && target.getColor() != this.color) {
                return true;
            }
            // En passant: destination empty and an ENEMY PAWN sits beside us.
            // Full validation (that the pawn just advanced two squares) is
            // completed in Game.makeMove() via the move history.
            Piece adjacent = board.getPiece(new Position(from.getRow(), to.getCol()));
            return board.getPiece(to) == null
                    && adjacent != null
                    && adjacent.getPieceType() == PieceType.PAWN
                    && adjacent.getColor() != this.color;
        }

        return false;
    }
}
