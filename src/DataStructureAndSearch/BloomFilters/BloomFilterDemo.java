package DataStructureAndSearch.BloomFilters;

import DataStructureAndSearch.BloomFilters.Models.BloomFilterConfig;
import DataStructureAndSearch.BloomFilters.Service.BloomFilter;
import DataStructureAndSearch.BloomFilters.Strategies.FNVHashStrategy;

public class BloomFilterDemo {
    public static void main(String[] args) {
        System.out.println("=== Bloom Filter Demo ===\n");

        System.out.println("1. Creating Bloom filter (expected: 1000, FP rate: 1%)");
        BloomFilter filter = new BloomFilter.Builder(1000)
                .falsePositiveRate(0.01)
                .build();
        BloomFilterConfig config = filter.getConfig();
        System.out.println("   Bit array size: " + config.getBitArraySize());
        System.out.println("   Hash functions: " + config.getNumHashFunctions());
        System.out.println("   Using default MurmurHashStrategy");

        filter.add("apple");
        filter.add("banana");
        filter.add("cherry");
        System.out.println("   Added: apple, banana, cherry");

        System.out.println("\n2. Checking membership");
        System.out.println("   mightContain('apple')  = " + filter.mightContain("apple"));
        System.out.println("   mightContain('banana') = " + filter.mightContain("banana"));
        System.out.println("   mightContain('cherry') = " + filter.mightContain("cherry"));
        System.out.println("   mightContain('grape')  = " + filter.mightContain("grape"));
        System.out.println("   mightContain('mango')  = " + filter.mightContain("mango"));

        System.out.println("\n3. Creating filter with FNV hash strategy");
        BloomFilter fnvFilter = new BloomFilter.Builder(1000)
                .falsePositiveRate(0.01)
                .hashStrategy(new FNVHashStrategy())
                .build();

        fnvFilter.add("hello");
        fnvFilter.add("world");
        System.out.println("   Added: hello, world");
        System.out.println("   mightContain('hello') = " + fnvFilter.mightContain("hello"));
        System.out.println("   mightContain('world') = " + fnvFilter.mightContain("world"));
        System.out.println("   mightContain('foo')   = " + fnvFilter.mightContain("foo"));

        System.out.println("\n4. Testing clear()");
        filter.clear();
        System.out.println("   Cleared the filter");
        System.out.println("   mightContain('apple')  = " + filter.mightContain("apple"));
        System.out.println("   mightContain('banana') = " + filter.mightContain("banana"));

        System.out.println("\n5. False positive demonstration");
        BloomFilter smallFilter = new BloomFilter.Builder(10)
                .falsePositiveRate(0.1)
                .build();
        smallFilter.add("cat");
        smallFilter.add("dog");
        smallFilter.add("bird");
        System.out.println("   Small filter (expected: 10, FP rate: 10%)");
        System.out.println("   Added: cat, dog, bird");

        int falsePositives = 0;
        String[] testWords = {"fish", "lion", "bear", "wolf", "deer",
                "frog", "hawk", "duck", "goat", "seal"};
        for (String word : testWords) {
            if (smallFilter.mightContain(word)) {
                falsePositives++;
                System.out.println("   False positive: '" + word + "'");
            }
        }
        System.out.println("   False positives: " + falsePositives + "/" + testWords.length);

        System.out.println("\n=== Demo Complete ===");
    }
}
