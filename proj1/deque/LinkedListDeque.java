package deque;
import java.util.Iterator;

// we use circular linked list for this project, which is recommended by professor Josh
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    // This is nested class
    private class Node {
        private T item;
        private Node next;
        private Node pre;

        private Node(T data, Node node) {
            item = data;
            next = node;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel.next);
        newNode.pre = sentinel;
        sentinel.next.pre = newNode;
        sentinel.next = newNode;

        size = size + 1;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.pre.next);
        newNode.pre = sentinel.pre;
        sentinel.pre.next = newNode;
        sentinel.pre = newNode;

        size = size + 1;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;

        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }

        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) { return null; }

        Node result = sentinel.next;

        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;

        size = size - 1;
        return result.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) { return null; }

        Node result = sentinel.pre;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;

        size = size - 1;
        return result.item;
    }

    @Override
    public T get(int index) {
        if (index >= size) { return null; }

        Node p = sentinel.next;
        int count = 0;

        while (index != count) {
            p = p.next;
            count++;
        }

        return p.item;
    }

    private T getHelper(int index, Node newNode) {
        if (index == 0) {
            return newNode.item;
        } else {
            return getHelper(index - 1, newNode.next);
        }
    }

    public T getRecursive(int index)
    {
        return getHelper(index, sentinel.next);
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int count;
        private Node p;

        private LinkedListDequeIterator() {
            count = 0;
            p = sentinel.next;
        }

        public boolean hasNext() {
            return p.item != null;
        }

        public T next() {
            T returnValue = p.item;
            p = p.next;
            return returnValue;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; }
        if (!(o instanceof Deque)) { return false; }

        Deque<T> oas = (Deque<T>) o;
        if (this.size != oas.size()) { return false; }

        for (int i = 0; i < size; i++) {
            if (!oas.get(i).equals(this.get(i))) { return false; }
        }

        return true;
    }
}
