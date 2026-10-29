package Game_Question_Practice.Tic_Tac_Toe;

public class TicTacToe {
    public static void main(String[] args) {
                Player alice = new Player("Alice", Symbol.X);
                Player bob = new Player("Bob", Symbol.O);

                Game game = new Game(alice, bob, 3);

                System.out.println("========== TIC TAC TOE ==========");

                // Alice (X) completes the top row and wins
                game.makeMove(0, 0);  // X at (0,0)
                game.makeMove(1, 0);  // O at (1,0)
                game.makeMove(0, 1);  // X at (0,1)
                game.makeMove(1, 1);  // O at (1,1)
                game.makeMove(0, 2);  // X at (0,2) - Alice wins!

                game.printBoard();

                System.out.println("Result: " + game.getStatus());
                Player winner = game.getWinner();
                if (winner != null) {
                    System.out.println("Winner: " + winner.getName());
                }
    }
}
