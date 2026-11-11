package Game_Question_Practice.Chess.Pieces;

import Game_Question_Practice.Chess.Enums.Color;
import Game_Question_Practice.Chess.Enums.PieceType;
import Game_Question_Practice.Chess.Models.Board;
import Game_Question_Practice.Chess.Models.Position;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // Must move in a straight line (horizontal or vertical, not both)
        if (rowDiff != 0 && colDiff != 0) {
            return false;
        }

        // Check path is clear
        if (!isPathClear(board, from, to)) {
            return false;
        }

        Piece target = board.getPiece(to);
        return target == null || target.getColor() != this.color;
    }

    private boolean isPathClear(Board board, Position from, Position to) {
        int rowDir = Integer.signum(to.getRow() - from.getRow());
        int colDir = Integer.signum(to.getCol() - from.getCol());

        int currentRow = from.getRow() + rowDir;
        int currentCol = from.getCol() + colDir;

        while (currentRow != to.getRow() || currentCol != to.getCol()) {
            if (board.getPiece(new Position(currentRow, currentCol)) != null) {
                return false;
            }
            currentRow += rowDir;
            currentCol += colDir;
        }
        return true;
    }
}
