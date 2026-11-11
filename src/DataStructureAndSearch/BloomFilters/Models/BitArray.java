package DataStructureAndSearch.BloomFilters.Models;

public class BitArray {
    private final boolean[] bits;
    private final int size;

    public BitArray(int size) {
        this.bits = new boolean[size];
        this.size = size;
    }

    public void set(int position) {
        // TODO: Mark the bit at `position` as set (true).
        bits[position]=true;
    }

    public boolean get(int position) {
        // TODO: Return whether the bit at `position` is set.
        return bits[position];
    }

    public void clear() {
        // TODO: Reset every bit back to false.
        for(int i=0;i<size;++i){
            bits[i]=false;
        }
    }

    public int size() {
        return size;
    }
}
