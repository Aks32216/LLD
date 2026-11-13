package DataStructureAndSearch.LRU_Cache;

public class DoublyLinkedList<K, V> {
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public DoublyLinkedList() {
        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        head.next=tail;
        tail.prev=head;
    }

    public void addFirst(Node<K, V> node) {
        // Steps:
        // 1. node.next = head.next
        // 2. node.prev = head
        // 3. head.next.prev = node
        // 4. head.next = node
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
        node.prev=node;
    }

    public void remove(Node<K, V> node) {
        // Steps:
        // 1. node.prev.next = node.next
        // 2. node.next.prev = node.prev
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void moveToFront(Node<K, V> node) {
        // Hint: Remove it first, then add it to front
        remove(node);
        addFirst(node);
    }

    public Node<K, V> removeLast() {
        // Steps:
        // 1. Check if list is empty (tail.prev == head), return null if so
        // 2. Get the last real node (tail.prev)
        // 3. Remove it using the remove() method
        // 4. Return the removed node
        if(tail.prev==head){
            return null;
        }
        Node<K,V> last=tail.prev;
        remove(last);
        return last;
    }
}
