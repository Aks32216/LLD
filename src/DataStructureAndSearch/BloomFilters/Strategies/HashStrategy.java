package DataStructureAndSearch.BloomFilters.Strategies;

public interface HashStrategy {
    int hash(String element, int seed, int bitArraySize);
}
