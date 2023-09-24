package deque;

import jh61b.junit.In;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    // add a few things to the list and check isEmpty() and size() are correct
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<Integer> array1 = new ArrayDeque<>();
        assertTrue("A newly initialized ArrayDeque should be empty", array1.isEmpty());

        array1.addFirst(1);
        assertEquals(1, array1.size());
        assertFalse("The ArrayDeque right now is not empty", array1.isEmpty());

        array1.addFirst(2);
        assertEquals(2, array1.size());

        System.out.println("Print the queue");
        array1.printDeque();
    }

    @Test
    public void addElementTest(){
        ArrayDeque<String> array1 = new ArrayDeque<>();

        array1.addLast("a");
        array1.addLast("b");
        array1.addFirst("c");
        array1.addLast("d");
        array1.addLast("e");
        array1.addFirst("f");
        array1.addLast("g");
        array1.addLast("h");

        System.out.println("Print the queue");
        array1.printDeque();
    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque<Integer> array1 = new ArrayDeque<>();

        array1.addFirst(1);
        array1.addFirst(2);
        array1.addFirst(3);
        array1.addFirst(4);
        array1.addFirst(5);
        array1.addFirst(6);
        array1.addFirst(7);
        array1.addFirst(8);

        array1.printDeque();
        int number1 = array1.removeFirst();
        int number2 = array1.removeFirst();
        assertEquals(number1, 8);
        assertEquals(number2, 7);
        array1.printDeque();
    }

    @Test
    public void testRemoveLast(){
        ArrayDeque<Integer> array1 = new ArrayDeque<>();

        array1.addLast(1);
        array1.addLast(2);
        array1.addLast(3);
        array1.addLast(4);
        array1.addLast(5);
        array1.addLast(6);
        array1.addLast(7);
        array1.addLast(8);

        array1.printDeque();

        int number1 = array1.removeFirst();
        System.out.println(number1);
        int number2 = array1.removeFirst();
        System.out.println(number2);
        int number3 = array1.removeFirst();
        System.out.println(number3);
        int number4 = array1.removeFirst();
        System.out.println(number4);
        int number5 = array1.removeFirst();
        System.out.println(number5);
        int number6 = array1.removeFirst();
        System.out.println(number6);
        int number7 = array1.removeFirst();
        System.out.println(number7);
        int number8 = array1.removeFirst();
        System.out.println(number8);
        array1.printDeque();

//        array1.addLast(1);
//        array1.addLast(2);
//        array1.addLast(3);
//        array1.addLast(4);
//        array1.addLast(5);
//        array1.addLast(6);
//        array1.addLast(7);
//        array1.addLast(8);
//
//        array1.printDeque();
    }

    @Test
    public void testGet(){
        ArrayDeque<Integer> array1 = new ArrayDeque<>();

        array1.addLast(1);
        array1.addLast(2);
        array1.addLast(3);
        array1.addLast(4);
        array1.addLast(5);
        array1.addLast(6);
        array1.addLast(7);
        array1.addLast(8);

        array1.printDeque();
        System.out.println(array1.get(0));
        System.out.println(array1.get(1));
        System.out.println(array1.get(2));
        System.out.println(array1.get(3));
        System.out.println(array1.get(4));
        System.out.println(array1.get(5));
        System.out.println(array1.get(6));
        System.out.println(array1.get(7));
        System.out.println(array1.get(8));
    }

    @Test
    public void testIterator(){
        ArrayDeque<Integer> array1 = new ArrayDeque<>();

        array1.addLast(1);
        array1.addLast(2);
        array1.addLast(3);
        array1.addLast(4);
        array1.addLast(5);
        array1.addLast(6);
        array1.addLast(7);
        array1.addLast(8);

        array1.printDeque();

//        Iterator<Integer> asser = array1.iterator();
//
//        while (asser.hasNext()){
//            System.out.println(asser.next());
//        }

        for (int i: array1){
            System.out.println(i);
        }
    }

    @Test
    public void testResizeA(){
        ArrayDeque<Integer> array1 = new ArrayDeque<>();
        ArrayDeque<Integer> array2 = new ArrayDeque<>();

        for (int i = 0; i < 150; i++){
            array1.addLast(i);
        }

        array1.printDeque();
//        for (int i = 0; i < 150; i++){
//            array2.addLast(i);
//        }

        //System.out.println(array1.equals(array2));
    }

    @Test
    public void testEqualA(){
        ArrayDeque<Integer> array1 = new ArrayDeque<>();
        ArrayDeque<Integer> array2 = new ArrayDeque<>();

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();

        for (int i = 0; i < 150; i++){
            lld1.addLast(i);
        }

        for (int i = 0; i < 130; i++){
            lld2.addLast(i);
        }

        for (int i = 0; i < 150; i++){
            array1.addLast(i);
        }

        for (int i = 0; i < 130; i++){
            array2.addLast(i);
        }

        System.out.println(array1.equals(array2));
        System.out.println(array1.equals(lld1));
        System.out.println(lld1.equals(lld2));
    }

    @Test
    public void testAddFirst_and_Removal(){
        ArrayDeque<Integer> a= new ArrayDeque<>();
        a.addFirst(0);
        a.addFirst(1);
        a.get(0);      // result should be 1
        a.addFirst(3);
        a.removeLast();    // result should be 0
        a.removeLast();    // result should be 1
        a.addLast(6);
        a.addLast(7);
        a.addFirst(8);
        a.addLast(9);
        a.addLast(10);
        a.removeFirst();  // result should be 8
        a.addFirst(12);
        a.addFirst(13);
        a.addLast(14);
        a.printDeque();
        a.get(0);      // result should be 13
        a.addFirst(16);
        a.addLast(17);
        a.printDeque();
        a.removeLast();  // result should 17
        a.printDeque();
        a.addLast(19);
        a.addFirst(20);
        a.addFirst(21);
        a.printDeque();
        a.removeLast();     // result should be 19
        a.printDeque();
    }
}


