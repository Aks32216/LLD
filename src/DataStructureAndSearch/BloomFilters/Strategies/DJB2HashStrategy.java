package DataStructureAndSearch.BloomFilters.Strategies;

public class DJB2HashStrategy implements HashStrategy {
    @Override
    public int hash(String element, int seed, int bitArraySize) {
        int hash = 5381 + seed;

        for (int i = 0; i < element.length(); i++) {
            hash = ((hash << 5) + hash) + element.charAt(i); // hash * 33 + c
        }

        return Math.abs(hash % bitArraySize);
    }
}
