package Collection_Framework;

/*
List interface implements collection interface
they provide ordered colection with duplicates allowed

list interface contains three main classes:
- ArrayList
- LinkedList
- Vector
- Stack (implements vector class)
*/

import java.util.ArrayList;
import java.util.List;

public class List_Interface {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();

        // add
        list.add(1);
        list.add(2);
        list.add(3);

        // get
        System.out.println(list.get(2));

        // set
        list.set(1,5);
        System.out.println(list.get(1));

        System.out.println(list);

        List<Integer> l=List.of(1,2,3,4,45,65,43); // returns an immutable list
        System.out.println(l);
        l.add(54); // throws UnsupportedOperationException
        System.out.println(l);
    }
}
