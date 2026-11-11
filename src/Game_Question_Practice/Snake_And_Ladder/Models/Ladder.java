package Game_Question_Practice.Snake_And_Ladder.Models;

public class Ladder extends BoardEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) {
            throw new IllegalArgumentException(
                    "Ladder bottom must be at a lower position than its top."
            );
        }
    }
}
