public class ArraySet<T> {
    private T[] arraySet;
    int size;

    public ArraySet(){
        arraySet = (T[])new Object[100];
        size = 0;
    }

    public boolean contains(T x){
        for (int i = 0; i < size; i++){
            if (arraySet[i].equals(x)) return true;
            // if (arraySet[i] == x) return true;  why this works?
        }

        return false;
    }

    public void add(T x){
        if (x == null){
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x) == true) {
            return;
        }
        else {
           arraySet[size] = x;
           size++;
        }
    }

    public int size(){
        return size;
    }

    public void print(){
        for (int i = 0; i < size; i++){
            System.out.println(arraySet[i]);
        }
    }

    public static void main(String[] args){
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        System.out.println(s.size());
        s.print();
    }
}
