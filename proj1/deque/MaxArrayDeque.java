package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;
    private int size = this.size();

    // constructor of MaxArrayDeque
    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }

        T maxElement = this.get(0);

        for (int i = 1; i < size(); i++) {
            T element = get(i);
            if (cmp.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }

        return maxElement;
    }

    public T max(Comparator<T> c) {
        // if (size == 0) { this is wrong
        if (size() == 0) {  // this is correct, why?
            // System.out.println("size is " + size);
            return null;
        }

        T maxElement = this.get(0);

        for (int i = 1; i < size(); i++) {
            T element = get(i);
            if (c.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }

        return maxElement;
    }

    private static class IntComparator1 implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }
    private static class IntComparator2 implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return b - a;
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    // This has to be private for API grade check, because it is not in the deque interface
    private static void main(String[] args) {
        MaxArrayDeque.IntComparator1 cmp1 = new MaxArrayDeque.IntComparator1();
        MaxArrayDeque.IntComparator2 cmp2 = new MaxArrayDeque.IntComparator2();

        MaxArrayDeque<Integer> a1 = new MaxArrayDeque<>(cmp1);
        a1.addLast(1);
        a1.addLast(2);
        a1.addLast(3);

        System.out.println(a1.max());
        System.out.println(a1.max(cmp2));

        MaxArrayDeque.StringComparator cmp3 = new MaxArrayDeque.StringComparator();
        MaxArrayDeque<String> s1 = new MaxArrayDeque<>(cmp3);

        s1.addLast("abc");
        s1.addLast("sss");
        s1.addLast("zaa");
        s1.addLast("zab");
        System.out.print(s1.max());
    }
}
