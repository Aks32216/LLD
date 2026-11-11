package Game_Question_Practice.Snake_And_Ladder.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Integer> snakesAndLadders;

    public Board(int size, List<BoardEntity> entities) {
        this.size = size;
        this.snakesAndLadders = new HashMap<>();

        for (BoardEntity entity : entities) {
            int start = entity.getStart();
            int end = entity.getEnd();

            if (start < 1 || start > size || end < 1 || end > size) {
                throw new IllegalArgumentException(
                        "Entity positions must be within 1 and " + size + "."
                );
            }
            if (snakesAndLadders.containsKey(start)) {
                throw new IllegalArgumentException(
                        "Two entities cannot share the same start cell: " + start + "."
                );
            }
            snakesAndLadders.put(start, end);
        }
    }

    public int getSize() {
        return size;
    }

    public int getFinalPosition(int position) {
        // TODO: Return the final position after applying any snake or ladder.
        // Look up `position` in the snakes-and-ladders map.
        // If an entry exists, return its mapped end position.
        // Otherwise return `position` unchanged.
        return snakesAndLadders.getOrDefault(position,position);
    }
}
