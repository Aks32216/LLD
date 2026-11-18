package DataStructureAndSearch.Search_AutoComplete.Core;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean isEndOfWord;
    private int frequency;

    Map<Character, TrieNode> getChildren() {
        return children;
    }

    boolean isEndOfWord() {
        return isEndOfWord;
    }

    void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    int getFrequency() {
        return frequency;
    }

    void incrementFrequency() {
        this.frequency++;
    }
}
