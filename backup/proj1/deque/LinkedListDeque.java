package deque;

import java.util.Iterator;

// we use circular linked list for this project, which is recommended by professor Josh
public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{

    // This is nested class
    private class Node{
        public T item;
        public Node next;
        public Node pre;

        public Node(T data, Node node){
            item = data;
            next = node;
        }
    }

    int size;
    Node sentinel;

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    @Override
    public void addFirst(T item){
        Node newNode = new Node(item, sentinel.next);
        newNode.pre = sentinel;
        sentinel.next.pre = newNode;
        sentinel.next = newNode;

        size = size + 1;
    }

    @Override
    public void addLast(T item){
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
    public void printDeque(){
        Node p = sentinel.next;

        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }

        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size == 0) return null;

        Node result = sentinel.next;

        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;

        size = size - 1;
        return result.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;

        Node result = sentinel.pre;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;

        size = size - 1;
        return result.item;
    }

    @Override
    public T get(int index){
        if (index >= size) return null;

        Node p = sentinel.next;
        int count = 0;

        while (index != count){
            p = p.next;
            count++;
        }

        return p.item;
    }

    public T getHelper(int index, Node newNode){
        if (index == 0) {
            return newNode.item;
        }
        else {
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

        private LinkedListDequeIterator(){
            count = 0;
            p = sentinel.next;
        }

        public boolean hasNext(){
            if (p.item == null) return false;
            else return true;
        }

        public T next(){
            T returnValue = p.item;
            p = p.next;
            return returnValue;
        }
    }

    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    // ask about equal method. Why it does not work?
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size != this.size) return false;

        Node p1 = this.sentinel.next;
        Node p2 = other.sentinel.next;

        while (p1 != this.sentinel) {
            // System.out.println("p1 value is " + p1.item + "p2 value is " + p2.item);
             if (!p1.item.equals(p2.item)) {
                 // System.out.println("p1 value is " + p1.item + " p2 value is " + p2.item);
                return false;
             }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }
}
