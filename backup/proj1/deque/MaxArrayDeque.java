package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> cmp;

    // constructor of MaxArrayDeque
    public MaxArrayDeque(Comparator<T> c){
        super();
        cmp = c;
    }

    public T max(){
        if (size() == 0) return null;

        int realIndex = this.arrayIndex(0);
        T maxElement = items[realIndex];

        for (int i = 1; i < size(); i++){
            realIndex = this.arrayIndex(i);
            T element = items[realIndex];
            if (cmp.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }

        return maxElement;
    }

    public T max(Comparator<T> c){
        if (size() == 0) return null;

        int realIndex = this.arrayIndex(0);
        T maxElement = items[realIndex];

        for (int i = 1; i < size(); i++){
            realIndex = this.arrayIndex(i);
            T element = items[realIndex];
            if (c.compare(element, maxElement) > 0) {
                maxElement = element;
            }
        }

        return maxElement;
    }

    public static class intComparator1 implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b){
            return a - b;
        }
    }

    public static class intComparator2 implements Comparator<Integer> {
        public int compare(Integer a, Integer b){
            return b - a;
        }
    }

    public static class stringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        MaxArrayDeque.intComparator1 cmp1 = new MaxArrayDeque.intComparator1();
        MaxArrayDeque.intComparator2 cmp3 = new MaxArrayDeque.intComparator2();

        MaxArrayDeque<Integer> a1 = new MaxArrayDeque<>(cmp1);
        a1.addLast(1);
        a1.addLast(2);
        a1.addLast(3);

        int result = a1.max();
        System.out.println(result);
        System.out.println(a1.max(cmp3));

        MaxArrayDeque.stringComparator cmp2 = new MaxArrayDeque.stringComparator();
        MaxArrayDeque<String> s1 = new MaxArrayDeque<>(cmp2);
        s1.addLast("abc");
        s1.addLast("sdfsdf");
        s1.addLast("zss");
        System.out.print(s1.max());
    }
}
