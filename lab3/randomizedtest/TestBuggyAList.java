package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> a = new AListNoResizing();
        BuggyAList<Integer> b = new BuggyAList();

        a.addLast(4);
        a.addLast(5);
        a.addLast(6);

        b.addLast(4);
        b.addLast(5);
        b.addLast(6);

        assertEquals(a.size(), b.size());
        int number1 = 0, number2 = 0;
        number1 = a.getLast();
        number2 = b.getLast();
        assertEquals(number1, number2);

        number1 = a.getLast();
        number2 = b.getLast();
        assertEquals(number1, number2);

        number1 = a.getLast();
        number2 = b.getLast();
        assertEquals(number1, number2);
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = B.size();
                // System.out.println("size1: " + size1 + "size2: " + size2);
                assertEquals(size1, size2);
            }
            else if (operationNumber == 2){
                if (L.size() == 0) continue;
                int number1 = L.getLast();
                int number2 = B.getLast();
                // System.out.println("getLast and the last value is " + number1 + " " + number2);
                assertEquals(number1, number2);
            }
            else if (operationNumber == 3){
                if (L.size() == 0) continue;
                int number1 = L.removeLast();
                int number2 = B.removeLast();
                // System.out.println("remove the last item, and the value is " + number1 + " " + number2);
                assertEquals(number1, number2);
            }
        }
    }
}
