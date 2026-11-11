package Game_Question_Practice.Chess;


import Game_Question_Practice.Chess.Exception.ChessException;

import javax.swing.text.Position;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        Player white = new Player("Alice", Color.WHITE);
        Player black = new Player("Bob", Color.BLACK);
        Game game = new Game(white, black);

        System.out.println("========== CHESS GAME: Alice (White) vs Bob (Black) ==========\n");

        // Scholar's Mate (4-move checkmate)
        System.out.println("--- Move 1: White Pawn e2 -> e4 ---");
        game.makeMove(new Position(6, 4), new Position(4, 4));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\n--- Move 2: Black Pawn e7 -> e5 ---");
        game.makeMove(new Position(1, 4), new Position(3, 4));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\n--- Move 3: White Bishop f1 -> c4 ---");
        game.makeMove(new Position(7, 5), new Position(4, 2));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\n--- Move 4: Black Knight b8 -> c6 ---");
        game.makeMove(new Position(0, 1), new Position(2, 2));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\n--- Move 5: White Queen d1 -> h5 ---");
        game.makeMove(new Position(7, 3), new Position(3, 7));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\n--- Move 6: Black Knight g8 -> f6 ---");
        game.makeMove(new Position(0, 6), new Position(2, 5));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\n--- Move 7: White Queen h5 -> f7 (Scholar's Mate!) ---");
        game.makeMove(new Position(3, 7), new Position(1, 5));
        System.out.println("Status: " + game.getStatus());

        System.out.println("\nGame Over! Final status: " + game.getStatus());
        System.out.println("Move history (" + game.getMoveHistory().size() + " moves):");
        for (Move move : game.getMoveHistory()) {
            System.out.println("  " + move);
        }

        // Try making a move after checkmate
        System.out.println("\n--- Attempt move after checkmate ---");
        try {
            game.makeMove(new Position(0, 3), new Position(1, 3));
        } catch (ChessException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
