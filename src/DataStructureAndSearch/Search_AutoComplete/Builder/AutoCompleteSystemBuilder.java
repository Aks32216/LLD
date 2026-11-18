package DataStructureAndSearch.Search_AutoComplete.Builder;

import DataStructureAndSearch.Search_AutoComplete.AutoCompleteSystem;
import DataStructureAndSearch.Search_AutoComplete.Strategy.FrequencyBasedRanking;
import DataStructureAndSearch.Search_AutoComplete.Strategy.RankingStrategy;

public class AutoCompleteSystemBuilder {
    private RankingStrategy rankingStrategy = new FrequencyBasedRanking(); // Default strategy
    private int maxSuggestions = 10; // Default limit

    public AutoCompleteSystemBuilder withRankingStrategy(RankingStrategy strategy) {
        this.rankingStrategy = strategy;
        return this;
    }

    public AutoCompleteSystemBuilder withMaxSuggestions(int max) {
        this.maxSuggestions = max;
        return this;
    }

    public AutoCompleteSystem build() {
        return new AutoCompleteSystem(rankingStrategy, maxSuggestions);
    }
}
