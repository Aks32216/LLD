package Game_Question_Practice.Chess.Pieces;

import Game_Question_Practice.Chess.Enums.Color;
import Game_Question_Practice.Chess.Enums.PieceType;
import Game_Question_Practice.Chess.Models.Board;
import Game_Question_Practice.Chess.Models.Position;

public abstract class Piece {
    protected final Color color;
    protected final PieceType pieceType;
    protected boolean hasMoved;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
        this.hasMoved = false;
    }

    public abstract boolean canMove(Board board, Position from, Position to);

    public Color getColor() { return color; }
    public PieceType getPieceType() { return pieceType; }
    public boolean hasMoved() { return hasMoved; }
    public void setHasMoved(boolean moved) { this.hasMoved = moved; }
}