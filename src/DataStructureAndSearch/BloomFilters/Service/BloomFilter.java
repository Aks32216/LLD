package DataStructureAndSearch.BloomFilters.Service;

import DataStructureAndSearch.BloomFilters.Models.BitArray;
import DataStructureAndSearch.BloomFilters.Models.BloomFilterConfig;
import DataStructureAndSearch.BloomFilters.Strategies.HashStrategy;
import DataStructureAndSearch.BloomFilters.Strategies.MurmurHashStrategy;

public class BloomFilter {
    private final BloomFilterConfig config;
    private final BitArray bitArray;
    private final HashStrategy hashStrategy;

    private BloomFilter(BloomFilterConfig config, HashStrategy hashStrategy) {
        this.config = config;
        this.bitArray = new BitArray(config.getBitArraySize());
        this.hashStrategy = hashStrategy;
    }

    // Synchronized to ensure all k bits are set atomically
    public synchronized void add(String element) {
        // TODO: Add `element` to the filter.
        // Reject a null or empty element by raising/throwing the same error the original code used.
        // For each of the k hash functions (seeds 0 to numHashFunctions - 1), hash the element to a position and set that bit.
        // Hold the lock around the bit updates so all k bits are set atomically.
        if(element==null || element.isBlank()){
            throw new IllegalArgumentException("Expected elements must contain value");
        }
        int numHashFunctions=this.config.getNumHashFunctions();
        int bitArraySize=this.bitArray.size();
        for(int i=0;i<numHashFunctions;++i){
            int position=hashStrategy.hash(element,i,bitArraySize);
            bitArray.set(position);
        }
    }

    // Synchronized to ensure consistent reads across all k positions
    public synchronized boolean mightContain(String element) {
        // TODO: Return whether `element` might be in the filter.
        // Reject a null or empty element by raising/throwing the same error the original code used.
        // For each of the k hash functions (seeds 0 to numHashFunctions - 1), hash the element to a position and check that bit.
        // If any bit is not set, return false immediately. If all k bits are set, return true.
        // Hold the read lock around the checks so the reads are consistent.
        if(element==null || element.isBlank()){
            throw new IllegalArgumentException("Expected elements must contain value");
        }
        int numHashFunctions=this.config.getNumHashFunctions();
        int bitArraySize=this.bitArray.size();

        for(int i=0;i<numHashFunctions;++i){
            int position=hashStrategy.hash(element,i,bitArraySize);
            if(!bitArray.get(position)){
                return false;
            }
        }
        return true;
    }

    public synchronized void clear() {
        // TODO: Reset the filter so it contains no elements.
        // Hold the lock and clear the underlying bit array.
        this.bitArray.clear();
    }

    public BloomFilterConfig getConfig() {
        return config;
    }

    // ---- Builder ----

    public static class Builder {
        private final int expectedElements;
        private double falsePositiveRate = 0.01;
        private HashStrategy hashStrategy = new MurmurHashStrategy();

        public Builder(int expectedElements) {
            this.expectedElements = expectedElements;
        }

        public Builder falsePositiveRate(double rate) {
            this.falsePositiveRate = rate;
            return this;
        }

        public Builder hashStrategy(HashStrategy strategy) {
            this.hashStrategy = strategy;
            return this;
        }

        public BloomFilter build() {
            if (expectedElements <= 0) {
                throw new IllegalArgumentException("Expected elements must be positive");
            }
            if (falsePositiveRate <= 0 || falsePositiveRate >= 1) {
                throw new IllegalArgumentException(
                        "False positive rate must be between 0 and 1 (exclusive)");
            }

            BloomFilterConfig config = new BloomFilterConfig(expectedElements, falsePositiveRate);
            return new BloomFilter(config, hashStrategy);
        }
    }
}
