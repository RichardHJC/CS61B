package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        // T[] items = (T[])  new Object[8]; 为什么这个是错的？ 因为items前面已经定义过了
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity){
        T[] newItems = (T[]) new Object[capacity];

        int realIndex = 0;
        for (int i = 0; i < size; i++){
            realIndex = arrayIndex(i);
            newItems[capacity / 4 + i] = items[realIndex];
        }

        items = newItems;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    @Override
    public void addFirst(T data) {
        if (size == items.length - 2){
            resize((int) (items.length * 2));
        }

        items[nextFirst] = data;
        size++;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        }
        else{
            nextFirst--;
        }
    }

    @Override
    public void addLast(T data){
        if (size == items.length - 2){
            resize((int)(items.length * 2));
        }

        items[nextLast] = data;
        size++;

        if (nextLast == items.length - 1){
            nextLast = 0;
        }
        else{
            nextLast++;
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for (int i = 0; i < items.length; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size == 0) return null;

        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }

        T value = getFirst();
        int firstLocation = arrayIndex(0);
        items[firstLocation] = null;
        size--;

        nextFirst = firstLocation;

        return value;
    }

    private T getFirst(){
        int realIndex = arrayIndex(0);
        return items[realIndex];
    }

    private T getLast(){
        int realIndex = arrayIndex(size - 1);
        return items[realIndex];
    }

    @Override
    public T removeLast(){
        if (size == 0) return null;

        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }

        T lastNumber = getLast();
        int lastIndex = arrayIndex(size - 1);
        items[lastIndex] = null;
        size--;

        nextLast = arrayIndex(size - 1);
        return lastNumber;
    }

    // return the address of the new location
    @Override
    public T get(int index){
        if (index >= size) return null;

        int realIndex = arrayIndex(index);
        return items[realIndex];
    }

    public int arrayIndex(int index){
        if (nextFirst + 1 + index >= items.length){
            return nextFirst + 1 + index - items.length;
        }
        else{
            return nextFirst + 1 + index;
        }
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizardPosition;
        private int count;

        public ArrayDequeIterator(){
            wizardPosition = 0;
            count = 0;
        }

        public boolean hasNext() {
            return count < size;
        }

        public T next(){
            int realIndex = arrayIndex(wizardPosition);
            T returnItem = items[realIndex];
            wizardPosition++;
            count++;
            return returnItem;
        }
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;

        if (this.size != other.size) return false;
        int count = 0;

        while (count < size){
            int realIndex = arrayIndex(count);
            // System.out.println("array1 item is " + this.items[realIndex] + " array2 item is " + other.items[realIndex]);

            if (! this.items[realIndex].equals(other.items[realIndex]))
            {
                // System.out.println("array1 item is " + this.items[realIndex] + " array2 item is " + other.items[realIndex]);
                return false;
            }
            count++;
        }

        return true;
    }
}
