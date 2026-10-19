package Collection_Framework;

/*
Java iterable interface says that any object that implements iterable
class must be iterable. And they must implement hasNext and next method

iterable interface provides two methods:
- hasNext()
- next()

Iterable has iterator() method which actually contains the above two methods

Thus it gives a clean interface which decouples client from knowing
internal storage structure of a class, and iteracte through them.


while(iter.hasNext()){
   System.out.println(iter.next());
}
for...each loop is a syntax sugar over the above code.
both are interchangable

Iterator class also has a method call remove() which can remove elements from iterator.
Catch:
while iterating though any collection, if we modify the collection,
then iterator will raise ConcurrentModificationError.
this is because iterator will detect that collection has changed, and would not be able to
was process and waht was not.

*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Iterable_Interface {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(10);
//        list.add(29);
//        list.add(123);
//        list.add(1844);
//
//        Iterator<Integer> iter=list.iterator();
//        while(iter.hasNext()){
//            System.out.println(iter.next());
//        }

        // even if underlying ds changes, it gives a clean interface to iterate
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(29);
        list.add(123);
        list.add(1844);

//        Iterator<Integer> iter=list.iterator();
//        while(iter.hasNext()){
//            System.out.println(iter.next());
//        }
//
//        // both has same effect
//        for(Integer i:list){
//            System.out.println(i);
//        }

        Iterator<Integer> rem=list.iterator();
        while (rem.hasNext()) {
            Integer value = rem.next();
            System.out.println(value);

            if (value == 29) {
                list.remove(value);  // Unsafe removal
            }
        }
    }
}
