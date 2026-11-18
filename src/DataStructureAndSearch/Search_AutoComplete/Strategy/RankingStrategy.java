package DataStructureAndSearch.Search_AutoComplete.Strategy;

import DataStructureAndSearch.Search_AutoComplete.Core.Suggestion;

import java.util.List;

public interface RankingStrategy {
    List<Suggestion> rank(List<Suggestion> suggestions);
}