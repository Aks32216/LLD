package Game_Question_Practice.Chess.Pieces;

import Game_Question_Practice.Chess.Enums.Color;
import Game_Question_Practice.Chess.Enums.PieceType;
import Game_Question_Practice.Chess.Models.Board;
import Game_Question_Practice.Chess.Models.Position;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());

        // L-shape: (2,1) or (1,2)
        boolean isLShape = (rowDiff == 2 && colDiff == 1)
                || (rowDiff == 1 && colDiff == 2);

        if (!isLShape) {
            return false;
        }

        // Knight can jump over pieces, so no path clearance check needed
        Piece target = board.getPiece(to);
        return target == null || target.getColor() != this.color;
    }
}
