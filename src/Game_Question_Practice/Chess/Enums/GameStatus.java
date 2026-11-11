package Game_Question_Practice.Chess.Enums;

public enum GameStatus {
    ACTIVE,      // Normal play
    CHECK,       // Current player's king is under attack
    CHECKMATE,   // Current player has no legal move to escape check
    STALEMATE,   // Current player has no legal moves but isn't in check
    RESIGNED     // A player voluntarily surrendered
}
