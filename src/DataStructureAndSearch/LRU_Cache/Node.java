package DataStructureAndSearch.LRU_Cache;

public class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    Node(K key, V value) {
        // TODO: Initialize key and value
        // Leave prev and next as null (they'll be set by the list)
        this.key=key;
        this.value=value;
    }
}
