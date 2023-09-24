/** Array based list.
 *  @author Josh Hug
 */
public class AList {
    private int size = 0;
    private int[] array;

    /** Creates an empty list. */
    public AList() {
        size = 0;
        array = new int[100];
    }

    public void resizeArray(int size){
        int[] temp = new int[size * 2];

        for (int i = 0; i < size; i++){
            temp[i] = array[i];
        }

        array = temp;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == array.length){
            resizeArray(size);
        }

        array[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast()
    {
        return array[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i)
    {
        return array[i];
    }

    /** Returns the number of items in the list. */
    public int size()
    {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast()
    {
        int lastNumber = array[size - 1];
        array[size - 1] = 0;
        size--;
        return lastNumber;
    }
} 