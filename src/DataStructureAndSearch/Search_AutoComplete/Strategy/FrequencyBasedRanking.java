package DataStructureAndSearch.Search_AutoComplete.Strategy;

import DataStructureAndSearch.Search_AutoComplete.Core.Suggestion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FrequencyBasedRanking implements RankingStrategy {
    @Override
    public List<Suggestion> rank(List<Suggestion> suggestions) {
        return suggestions.stream()
                .sorted(Comparator.comparingInt(Suggestion::getWeight).reversed().thenComparing(Suggestion::getWord))
                .collect(Collectors.toList());
    }
}
