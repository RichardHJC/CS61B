package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        // T[] items = (T[])  new Object[8]; 为什么这个是错的？ items前面已经定义过了
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        for (int i = 0; i < size(); i++) {
            T value = get(i);
            a[capacity / 4 + i] = value;
        }

        items = a;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    @Override
    public void addFirst(T data) {
        if (size == items.length - 2) {
            resize(items.length * 2);
        }

        items[nextFirst] = data;
        size++;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
    }

    @Override
    public void addLast(T data) {
        if (size == items.length - 2) {
            resize(items.length * 2);
        }

        items[nextLast] = data;
        size++;

        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) { return null; }

        if (size < items.length / 4 && size > 8) {
            resize(items.length / 2);
        }

        T value = getFirst();
        int firstLocation = arrayIndex(0);
        items[firstLocation] = null;
        size--;

        nextFirst = firstLocation;

        return value;
    }

    private T getFirst() {
        int realIndex = arrayIndex(0);
        return items[realIndex];
    }

    private T getLast() {
        int realIndex = arrayIndex(size - 1);
        return items[realIndex];
    }

    @Override
    public T removeLast() {
        if (size == 0) { return null; }

        if (size < items.length / 4 && size > 8) {
            resize(items.length / 2);
        }

        // Find the position of the last number and put it become null
        T lastNumber = getLast();
        int lastLocation = arrayIndex(size - 1);
        items[lastLocation] = null;
        size--;

        // nestLast position should be the original last position
        // because the last position is removed
        nextLast = lastLocation;
        return lastNumber;
    }

    // return the address of the new location
    @Override
    public T get(int index) {
        if (index >= size) { return null; }

        int realIndex = arrayIndex(index);
        return items[realIndex];
    }

    private int arrayIndex(int index) {
        if (nextFirst + 1 + index >= items.length) {
            return nextFirst + 1 + index - items.length;
        } else {
            return nextFirst + 1 + index;
        }
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizardPosition;
        private int count;

        private ArrayDequeIterator() {
            wizardPosition = 0;
            count = 0;
        }

        public boolean hasNext() {
            return count < size;
        }

        public T next() {
            int realIndex = arrayIndex(wizardPosition);
            T returnItem = items[realIndex];
            wizardPosition++;
            count++;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; }
        if (!(o instanceof Deque)) { return false; }

        Deque<T> oas = (Deque<T>) o;
        if (this.size != oas.size()) { return false; }

        int count = 0;
        while (count < size) {
            int realIndex = arrayIndex(count);
            if (!this.get(count).equals(oas.get(count))) { return false; }
            count++;
        }

        return true;
    }
}
