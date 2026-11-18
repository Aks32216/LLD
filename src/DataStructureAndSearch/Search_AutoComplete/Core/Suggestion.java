package DataStructureAndSearch.Search_AutoComplete.Core;

public class Suggestion {
    private final String word;
    private final int weight;

    public Suggestion(String word, int weight) {
        this.word = word;
        this.weight = weight;
    }

    public String getWord() {
        return word;
    }

    public int getWeight() {
        return weight;
    }
}
