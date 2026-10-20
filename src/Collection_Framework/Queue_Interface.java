package Collection_Framework;

import java.util.ArrayDeque;
import java.util.Queue;

public class Queue_Interface {
    public static void main(String[] args) {
        // single ended queue
        Queue<Integer> queue=new ArrayDeque<>();

        /*
        Main methods
        - offer/add -> earlier one being null safe
        - poll/remove
        - peek/element
        */

        queue.add(1); // can throw exception if insertion fails
        queue.offer(2);
        queue.offer(3);

        System.out.println(queue);
        System.out.println(queue.peek());
        Integer elem=queue.poll();
        System.out.println(elem);
        System.out.println(queue);

        // doubly ended queue
        ArrayDeque<Integer> deque=new ArrayDeque<>();

        /*
        Main methods, similar to queue, but additoinaly provides feature to add at last or fist
        */

        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(5);
        deque.pollFirst();
        System.out.println(deque);


        /*
        Stack -> uses deque only, but now only use one type of operation, either first or last, do not mix
        */

        ArrayDeque<Integer> stack=new ArrayDeque<>();
        stack.offerLast(1);
        stack.offerLast(2);
        stack.offerLast(5);
        System.out.println(stack);
        stack.pollLast();
        stack.pollLast();
        System.out.println(stack);

    }
}
