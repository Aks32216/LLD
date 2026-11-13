package DataStructureAndSearch.LRU_Cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        // TODO: Initialize capacity, create empty HashMap, create new DoublyLinkedList
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public synchronized V get(K key) {
        // Steps:
        // 1. If key not in map, return null
        // 2. Get the node from the map
        // 3. Move the node to front (mark as most recently used)
        // 4. Return the node's value
        if(!map.containsKey(key)){
            return null;
        }
        Node<K,V> cur=map.get(key);
        list.moveToFront(cur);
        return cur.value;
    }

    public synchronized void put(K key, V value) {
        // Case 1: Key already exists
        //   - Get the existing node
        //   - Update its value
        //   - Move it to front
        //
        // Case 2: Key is new
        //   - If at capacity, evict LRU item:
        //     - Remove last node from list
        //     - Remove its key from map
        //   - Create new node
        //   - Add to front of list
        //   - Add to map
        if(map.containsKey(key)){
            Node<K,V> cur=map.get(key);
            cur.value=value;
            list.moveToFront(cur);
        } else {
            if(map.size()>=capacity){
                Node<K,V> removed=list.removeLast();
                map.remove(removed.key);
            }
            Node<K,V> cur=new Node<>(key,value);
            list.addFirst(cur);
            map.put(key,cur);
        }
    }
}
