package DataStructureAndSearch.BloomFilters.Strategies;

public class MurmurHashStrategy implements HashStrategy {
    @Override
    public int hash(String element, int seed, int bitArraySize) {
        byte[] data = element.getBytes();
        int h = seed;

        for (byte b : data) {
            h ^= b;
            h *= 0x5bd1e995;
            h ^= (h >>> 13);
        }

        // Finalization mix
        h ^= (h >>> 16);
        h *= 0x85ebca6b;
        h ^= (h >>> 13);

        return Math.abs(h % bitArraySize);
    }
}
