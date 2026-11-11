package DataStructureAndSearch.BloomFilters.Strategies;

public class FNVHashStrategy implements HashStrategy {
    private static final int FNV_OFFSET_BASIS = 0x811c9dc5;
    private static final int FNV_PRIME = 0x01000193;

    @Override
    public int hash(String element, int seed, int bitArraySize) {
        int hash = FNV_OFFSET_BASIS ^ seed;

        for (int i = 0; i < element.length(); i++) {
            hash ^= element.charAt(i);
            hash *= FNV_PRIME;
        }

        return Math.abs(hash % bitArraySize);
    }
}
