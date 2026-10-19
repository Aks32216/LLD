package Collection_Framework;

/*
Collection Interface is parent of all the collections.
It provides all the generic methods that are supported by all types of collections.

*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Collection_Interface {
    public static void main(String[] args) {
        Collection<Integer> c=new ArrayList<>();

        c.add(1);
        c.add(2);
        c.add(3);
        c.add(4);

        System.out.println(c.size());
        System.out.println(c.isEmpty());

        System.out.println(c.contains(5));
        System.out.println(c.contains(1));

        Collection<Integer> set=new HashSet<>();

        // add -> boolean
        set.add(1);
        System.out.println(set.add(1)); // false, fails to insert

    }
}
