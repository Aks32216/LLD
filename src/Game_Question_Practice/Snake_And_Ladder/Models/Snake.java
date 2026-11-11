package Game_Question_Practice.Snake_And_Ladder.Models;


public class Snake extends BoardEntity {
    public Snake(int start, int end) {
        super(start, end);
        if (start <= end) {
            throw new IllegalArgumentException(
                    "Snake head must be at a higher position than its tail."
            );
        }
    }
}
