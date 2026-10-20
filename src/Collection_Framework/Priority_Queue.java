package Collection_Framework;

import java.util.PriorityQueue;

public class Priority_Queue {
    public static void main(String[] args) {
        // By default implmented as min-heap
        PriorityQueue<Integer> pq=new PriorityQueue();
        pq.offer(3);
        pq.offer(6);
        pq.offer(2);
        System.out.println(pq.peek());
        pq.poll();
        System.out.println(pq.peek());


        // Max Heap
        PriorityQueue<Integer> mpq=new PriorityQueue<>((a,b)->b-a);
        mpq.offer(1);
        mpq.offer(2);
        mpq.offer(124);
        mpq.offer(4);
        mpq.offer(5332);
        mpq.offer(6);
        System.out.println(mpq.peek());
    }
}
