package Game_Question_Practice.Chess.Pieces;

import Game_Question_Practice.Chess.Enums.Color;
import Game_Question_Practice.Chess.Enums.PieceType;
import Game_Question_Practice.Chess.Models.Board;
import Game_Question_Practice.Chess.Models.Position;

public class King extends Piece {
    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        // Normal king move: one square in any direction
        if (rowDiff <= 1 && colDiff <= 1 && (rowDiff + colDiff > 0)) {
            Piece target = board.getPiece(to);
            return target == null || target.getColor() != this.color;
        }

        // Castling: king moves two squares horizontally
        if (rowDiff == 0 && colDiff == 2 && !hasMoved) {
            return canCastle(board, from, to);
        }

        return false;
    }

    private boolean canCastle(Board board, Position from, Position to) {
        int row = from.getRow();
        int direction = to.getCol() > from.getCol() ? 1 : -1;
        int rookCol = direction == 1 ? 7 : 0;

        // Check that the rook is in place and hasn't moved
        Piece rook = board.getPiece(new Position(row, rookCol));
        if (rook == null || rook.getPieceType() != PieceType.ROOK
                || rook.hasMoved()) {
            return false;
        }

        // Check that all squares between king and rook are empty
        int startCol = Math.min(from.getCol(), rookCol) + 1;
        int endCol = Math.max(from.getCol(), rookCol);
        for (int col = startCol; col < endCol; col++) {
            if (board.getPiece(new Position(row, col)) != null) {
                return false;
            }
        }

        // Note: checking that the king doesn't pass through or land in check
        // is handled by the Game class during move validation
        return true;
    }
}
