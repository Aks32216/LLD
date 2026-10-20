package Collection_Framework;

/*
equals and hashcode contract
- if two values, return true for equals method, then their hashcode would be same
- if two values, return true for hashcode, it does not mean, their value would be equal

Internally uses Map
set
- no duplication
- search in constant time
- order not preserved

LinkedSet
- no duplication
- search in constant time
- order of insertion preserved

TreeSet - stores in BST structure
- no duplication
- search in log n time
- always sorted




map - stores key value pairs
- unique keys
- search in constant time
- order not preserved

LinkedHashMap
- unique keys
- search in constant time
- order of insertion preserved

HashTreeSet - stores in BST structure
- no duplication
- search in log n time
- always sorted

put-> hashes the key (multiple keys can end up at same location)
finds if keys exist, if yes, replaces the value, else creates a new node and return null

containsKey -> hashes the key, goes to bucket and search if that key exist.

*/

import java.util.*;

public class Set_And_Maps {
    public static void main(String[] args) {
        // Set
        System.out.println("-------------- [SET] ---------------");
        Set<String> set=new HashSet<>(); // internally translated as HashMap
        Set<String> set1=new LinkedHashSet<>(); // preserves order of insertion

        set.add("Amish");
        set.add("Anshuman");
        set.add("Suraj");

        System.out.println(set.contains("Amish"));

        for(String name:set){
            System.out.println(name);
        }

        set1.add("Amish");
        set1.add("Anshuman");
        set1.add("Suraj");

        for(String name:set1){
            System.out.println(name);
        }


        System.out.println("-------------- [MAP] ---------------");
        Map<String,Integer> mp=new HashMap<>();

        mp.put("Amish",1);
        mp.put("Anshuman",2);

        System.out.println(mp.containsKey("Amish"));
        System.out.println(mp.containsValue(3));
    }
}
