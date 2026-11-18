package DataStructureAndSearch.Search_AutoComplete;

import DataStructureAndSearch.Search_AutoComplete.Core.Suggestion;
import DataStructureAndSearch.Search_AutoComplete.Core.Trie;
import DataStructureAndSearch.Search_AutoComplete.Core.TrieNode;
import DataStructureAndSearch.Search_AutoComplete.Strategy.RankingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoCompleteSystem {
    private final Trie trie;
    private final RankingStrategy rankingStrategy;
    private final int maxSuggestions;

    public AutoCompleteSystem(RankingStrategy rankingStrategy, int maxSuggestions) {
        this.trie = new Trie();
        this.rankingStrategy = rankingStrategy;
        this.maxSuggestions = maxSuggestions;
    }

    public void addWord(String word) {
        trie.insert(word.toLowerCase());
    }

    public void addWords(List<String> words) {
        words.forEach(this::addWord);
    }

    public List<String> getSuggestions(String prefix) {
        TrieNode prefixNode = trie.searchPrefix(prefix.toLowerCase());
        if (prefixNode == null) {
            return Collections.emptyList();
        }

        List<Suggestion> rawSuggestions = trie.collectSuggestions(prefixNode, prefix.toLowerCase());
        List<Suggestion> rankedSuggestions = rankingStrategy.rank(rawSuggestions);

        return rankedSuggestions.stream()
                .limit(maxSuggestions)
                .map(Suggestion::getWord)
                .collect(Collectors.toList());
    }
}
