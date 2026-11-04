package Game_Question_Practice.Snake_And_Ladder;

class Dice {
    private final int minValue;
    private final int maxValue;

    public Dice(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    private static final int[] SEQUENCE = { 3, 4, 6, 6, 5, 4, 1, 1, 2, 2, 2, 5 };
    private static int sequenceIndex = 0;

    public int roll() {
        int value = SEQUENCE[sequenceIndex % SEQUENCE.length];
        sequenceIndex++;
        return value;
    }
}