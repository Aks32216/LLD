package Game_Question_Practice.Snake_And_Ladder.Service;

import Game_Question_Practice.Snake_And_Ladder.Enums.GameStatus;
import Game_Question_Practice.Snake_And_Ladder.Models.Board;
import Game_Question_Practice.Snake_And_Ladder.Models.BoardEntity;
import Game_Question_Practice.Snake_And_Ladder.Models.Dice;
import Game_Question_Practice.Snake_And_Ladder.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private GameStatus status;
    private Player winner;

    private Game(Builder builder) {
        this.board = builder.board;
        this.players = new LinkedList<>(builder.players);
        this.dice = builder.dice;
        this.status = GameStatus.NOT_STARTED;
    }

    public void play() {
        // TODO: Run the main game loop until a player wins.
        // 1. If there are fewer than 2 players, print "Cannot start game. At least 2 players are required." and return.
        // 2. Set status to RUNNING and print "Game started!".
        // 3. While the status is RUNNING: take the next player from the front of the queue and call takeTurn on them.
        // 4. If the game is still RUNNING after the turn, put that player back at the end of the queue.
        // 5. After the loop, print "Game Finished!".
        // 6. If there is a winner, print "The winner is <name>!".
        if(players.size()<2){
            System.out.println("Cannot start game. At least 2 players are required.");
            return;
        }
        this.status=GameStatus.RUNNING;
        System.out.println("Game Started!");
        while(this.status==GameStatus.RUNNING){
            Player curPlayer=players.poll();
            takeTurn(curPlayer);
            if(this.status==GameStatus.RUNNING){
                players.offer(curPlayer);
            }
        }
        System.out.println("Game Finished!");
        if(winner!=null){
            System.out.println("The winner is "+winner.getName());
        }
    }

    private void takeTurn(Player player) {
        // TODO: Play one turn for the given player and print the result.
        // 1. Roll the dice. Print a blank line then "<name>'s turn. Rolled a <roll>.".
        // 2. Compute nextPosition = current position + roll.
        // 3. If nextPosition is greater than the board size, print "Oops, <name> needs to land exactly on <size>. Turn skipped." and return.
        // 4. If nextPosition equals the board size, move the player there, set them as winner, set status to FINISHED, print "Hooray! <name> reached the final square <size> and won!" and return.
        // 5. Otherwise ask the board for the final position after snakes and ladders.
        // 6. If the final position is higher than nextPosition, print "Wow! <name> found a ladder at <nextPosition> and climbed to <finalPosition>.".
        // 7. If it is lower, print "Oh no! <name> was bitten by a snake at <nextPosition> and slid down to <finalPosition>.".
        // 8. Otherwise print "<name> moved from <currentPosition> to <finalPosition>.".
        // 9. Move the player to the final position.
        // 10. If the roll was 6, print "<name> rolled a 6 and gets another turn!" and take another turn for the same player.
        int diceNum= dice.roll();
        System.out.println("\n");
        System.out.println(player.getName()+"'s turn. Rolled a "+diceNum);
        int nextPosition= player.getPosition() + diceNum;
        if(nextPosition > board.getSize()){
            System.out.println("Oops, "+player.getName()+" needs to land exactly on "+board.getSize()+". Turn skipped.");
            return;
        }
        if(nextPosition== board.getSize()){
            player.setPosition(nextPosition);
            this.winner=player;
            this.status=GameStatus.FINISHED;
            System.out.println("Hooray! "+player.getName()+" reached the final square "+board.getSize()+" and won!");
            return;
        }
        int finalPosition= board.getFinalPosition(nextPosition);
        if(finalPosition > nextPosition) {
            System.out.println("Wow! " + player.getName() + " found a ladder at " + nextPosition + " and climbed to " + finalPosition + ".");
        } else if(finalPosition < nextPosition){
            System.out.println("Oh no! " + player.getName() + " was bitten by a snake at " + nextPosition + " and slid down to " + finalPosition + ".");
        } else {
            System.out.println(player.getName() + " moved from " + player.getPosition() + " to " + finalPosition + ".");
        }
        player.setPosition(finalPosition);
        if(diceNum==6){
            System.out.println(player.getName() + " rolled a 6 and gets another turn!");
            takeTurn(player);
        }
    }

    // Builder inner class
    public static class Builder {
        private Board board;
        private Queue<Player> players;
        private Dice dice;

        public Builder setBoard(int boardSize, List<BoardEntity> boardEntities) {
            this.board = new Board(boardSize, boardEntities);
            return this;
        }

        public Builder setPlayers(List<String> playerNames) {
            this.players = new LinkedList<>();
            for (String playerName : playerNames) {
                players.add(new Player(playerName));
            }
            return this;
        }

        public Builder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Game build() {
            if (board == null || players == null || dice == null) {
                throw new IllegalStateException("Board, Players, and Dice must be set.");
            }
            return new Game(this);
        }
    }
}
