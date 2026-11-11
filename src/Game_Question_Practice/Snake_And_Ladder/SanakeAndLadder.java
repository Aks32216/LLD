package Game_Question_Practice.Snake_And_Ladder;

import Game_Question_Practice.Snake_And_Ladder.Models.BoardEntity;
import Game_Question_Practice.Snake_And_Ladder.Models.Dice;
import Game_Question_Practice.Snake_And_Ladder.Models.Ladder;
import Game_Question_Practice.Snake_And_Ladder.Models.Snake;
import Game_Question_Practice.Snake_And_Ladder.Service.Game;

import java.util.Arrays;
import java.util.List;

public class SanakeAndLadder {
    public static void main(String[] args) {
        List<BoardEntity> boardEntities = List.of(
                new Snake(17, 7),
                new Snake(54, 34),
                new Snake(62, 19),
                new Snake(98, 79),
                new Ladder(3, 38),
                new Ladder(24, 33),
                new Ladder(42, 93),
                new Ladder(72, 84)
        );

        List<String> players = Arrays.asList("Alice", "Bob", "Charlie");

        Game game = new Game.Builder()
                .setBoard(100, boardEntities)
                .setPlayers(players)
                .setDice(new Dice(1, 6))
                .build();

        game.play();
    }
}