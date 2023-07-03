/*
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources Used: Lecture Notes, Zybooks, PublicTester.java
 *
 * This file is the custom tester for CSE 12 PA6 in Winter 2023.
 * It contains tests from methods from the MyDeque, MyStack and MyQueue 
 * to ensure that there are no errors. It uses JUnit tests. 
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The class CustomTester essentially tests the MyDeque.java, MyStack.java,
 *  and MyQueue.java in multiple ways to ensure that they have no errors. 
 */
public class CustomTester {

    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The deque to initialize
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
     static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
          deque.data = data;
          deque.size = size;
          deque.front = front;
          deque.rear = rear;
     }
    

     /**
      * Test the Deque constructor through various ways (exceptions and etc.)
      */
     @Test
     public void testDequeConstructor() {

        MyDeque<String> deque = new MyDeque<String>(1000);
        assertEquals("Capacity should be initialized to 10", 1000,
        deque.data.length);
        assertEquals("Size should be initialized to 0", 0, deque.size);
        assertEquals("Front should be initialized to 0", 0, deque.front);
        assertEquals("Rear should be initialized to 0", 0, deque.rear);
     }

    /** 
     * Test expandCapacity in multiple ways and situations 
     */
    @Test
     public void testExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = { 5, 6, 1, 10, 12, null, null, null, 9, 8};
        Integer[] finalOrdering = {9, 8, 5, 6, 1, 10, 12, null,
                null, null, null, null, null, null,null,null,null,null,null,
                null};
        initDeque(deque, orig, 7, 8, 4);

        deque.expandCapacity();

        assertEquals("Capacity should have doubled", 20, deque.data.length);
        assertEquals("Size should not have changed", 7, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 6, deque.rear);
        for (int i = 0; i < 20; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }

        Integer[] origa = { 3, 4, 5, 6, 1, 2};
        Integer[] finalOrderings = {1, 2, 3, 4, 5, 6,
                null, null, null, null,null,null};
        initDeque(deque, origa, 6, 4, 3);
        deque.expandCapacity();
        assertEquals("Capacity should have doubled", 12, deque.data.length);
        assertEquals("Size should not have changed", 6, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size - 1", 5, deque.rear);
        for (int i = 0; i < 12; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrderings[i], deque.data[i]);
        }

        MyDeque<Integer> dequeNull = new MyDeque<Integer>(0);
        Integer[] finalOrderingss = {null, null, null, null,
                null, null, null, null,null,null};
        dequeNull.expandCapacity();
        assertEquals("Capacity should be at 10", 10, dequeNull.data.length);
        assertEquals("Size should not have changed", 0, dequeNull.size);
        assertEquals("Front should be 0", 0, dequeNull.front);
        assertEquals("Rear should be at size - 1", 0, dequeNull.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrderingss[i], dequeNull.data[i]);
        }
     }

    /**
     *  Tests the Addfirst method from myDeque
     */
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {5, 4, 1, 4, 5, 6, 3, 9, 1, 2};
        Integer[] finalOrderings = {1, 2, 5, 4, 1, 4,
            5, 6, 3, 9,null,null,null,null,null,null,null,null,null,10};
        
        initDeque(deque, orig, 10, 8, 7);

        assertThrows("Checks to see if exception is thrown",
        NullPointerException.class, () -> {
            deque.addFirst(null);});

        deque.addFirst(10);

        assertEquals("Capacity should change if deque full", 20,
                deque.data.length);
        assertEquals("Should increment size", 11, deque.size);
        assertEquals("Front should change", 19, deque.front);
        assertEquals("Rear change when calling addFirst", 9,
                deque.rear);
        assertEquals("6 should have been inserted into index 2",
                10, deque.data[19]);
        
                for (int i = 0; i < 20; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderings[i], deque.data[i]);
                }
        
        MyDeque<Integer> deques = new MyDeque<>(0);
        Integer[] finalOrderingss = {10,null,null,null,null,null,null,null,
                null,null};
        deques.addFirst(10);

        assertEquals("Capacity should change if deque full", 10,
                deques.data.length);
        assertEquals("Should increment size", 1, deques.size);
        assertEquals("Front shouldn't change", 0, deques.front);
        assertEquals("Rear shouldn't change when calling", 0,
                deques.rear);
        assertEquals("6 should have been inserted into index 2",
                10, deques.data[0]);
        
                for (int i = 0; i < 10; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderingss[i], deques.data[i]);
                }

        
        MyDeque<Integer> deques2 = new MyDeque<>(0);
        Integer[] orig2 = {4,2,null,null,null,null,null,null,null,null};
        Integer[] finalOrderingss2 = {4,2,null,null,null,null,null,null,
                null,10};
        initDeque(deque, orig2, 2, 0, 1);
        deque.addFirst(10);

        assertEquals("Capacity shouldnt change if deque full", 10,
                deque.data.length);
        assertEquals("Should increment size", 3, deque.size);
        assertEquals("Front should change", 9, deque.front);
        assertEquals("Rear shouldn't change when calling", 1,
                deque.rear);
        assertEquals("6 should have been inserted into index 2",
                10, deque.data[9]);
        
                for (int i = 0; i < 10; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderingss2[i], deque.data[i]);
                }
    }


    /**
     * Tests the AddLast method from deque
     */
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {5, 4, 1, 4, 5, 6, 3, 9, 1, 2};
        Integer[] finalOrderings = {1, 2, 5, 4, 1, 4,
            5, 6, 3, 9,10,null,null,null,null,null,null,null,null,null};
        
        initDeque(deque, orig, 10, 8, 7);

        assertThrows("Checks to see if exception is thrown",
        NullPointerException.class, () -> {
            deque.addLast(null);});

        deque.addLast(10);

        assertEquals("Capacity should change if deque full", 20,
                deque.data.length);
        assertEquals("Should increment size", 11, deque.size);
        assertEquals("Front should change", 0, deque.front);
        assertEquals("Rear change when calling addLast", 10,
                deque.rear);
        assertEquals("10 should have been inserted into index 10",
                10, deque.data[10]);
        
                for (int i = 0; i < 20; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderings[i], deque.data[i]);
                }
        
        MyDeque<Integer> deques = new MyDeque<>(0);
        Integer[] finalOrderingss = {10,null,null,null,null,null,null,null,
                null,null};
        deques.addLast(10);

        assertEquals("Capacity should change if deque full", 10,
                deques.data.length);
        assertEquals("Should increment size", 1, deques.size);
        assertEquals("Front shouldn't change", 0, deques.front);
        assertEquals("Rear shouldn't change when calling", 0,
                deques.rear);
        assertEquals("10 should have been inserted into index 0",
                10, deques.data[0]);
        
                for (int i = 0; i < 10; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderingss[i], deques.data[i]);
                }

        
        MyDeque<Integer> deques2 = new MyDeque<>(0);
        Integer[] orig2 = {4,2,null,null,null,null,null,null,null,null};
        Integer[] finalOrderingss2 = {4,2,10,null,null,null,null,null,
                null,null};
        initDeque(deque, orig2, 2, 0, 1);
        deque.addLast(10);

        assertEquals("Capacity shouldnt change if deque full", 10,
                deque.data.length);
        assertEquals("Should increment size", 3, deque.size);
        assertEquals("Front shouldnt change", 0, deque.front);
        assertEquals("Rear should change when calling", 2,
                deque.rear);
        assertEquals("10 should have been inserted into index 2",
                10, deque.data[2]);
        
                for (int i = 0; i < 10; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderingss2[i], deque.data[i]);
                }


        Integer[] origs = {5, 4, 1, 4, 5, 6, 3, null, 1, 2};
        Integer[] finalOrderings2 = {5, 4, 1, 4, 5, 6, 3, 9, 1, 2};
        
        initDeque(deque, origs, 9, 8, 6);
        deque.addLast(9);

        assertEquals("Capacity should change if deque full", 10,
                deque.data.length);
        assertEquals("Should increment size", 10, deque.size);
        assertEquals("Front shouldnt change", 8, deque.front);
        assertEquals("Rear change when calling addLast", 7,
                deque.rear);
        assertEquals("10 should have been inserted into index 10",
                9, deque.data[7]);
        
                for (int i = 0; i < 10; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderings2[i], deque.data[i]);
                }


        Integer[] orig3 = {null,null,null,null,null,null,null,null,null,4};
        Integer[] finalOrderingss3 = {10,null,null,null,null,null,null,null,
                null,4};
        initDeque(deque, orig3, 1, 9, 9);
        deque.addLast(10);

        assertEquals("Capacity shouldnt change if deque full", 10,
                deque.data.length);
        assertEquals("Should increment size", 2, deque.size);
        assertEquals("Front shouldnt change", 9, deque.front);
        assertEquals("Rear should change when calling", 0,
                deque.rear);
        assertEquals("10 should have been inserted into index 0",
                10, deque.data[0]);
        
                for (int i = 0; i < 10; i++) {
                    assertEquals("Deque structure should be maintained",
                            finalOrderingss3[i], deque.data[i]);
                }

        

    }

    /**
     * Tests the RemoveFirst method from myDeque
     */
    @Test
    public void testRemoveFirst(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {5, 3, 4, 1, 5, 5, 5, 5, 5, 5};
        initDeque(deque, orig, 10, 2, 1);

        assertEquals("Element removed should be returned", 4,
                deque.removeFirst().intValue());

        assertEquals("Array length shouldn't be changed", 10,
                deque.data.length);
        assertEquals("Size should decrement", 9, deque.size);
        assertEquals("Front should move one index after removing from " +
                "non-empty deque", 3, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 1,
                deque.rear);
        assertEquals("Index 2 should remain unchanged", 3,
                deque.data[1]);
        assertEquals("Index 3 should remain unchanged", 1,
                deque.data[3]);
        assertNull("Index 1 should have been set to null", deque.data[2]);

        Integer[] origs = {5};
        initDeque(deque, origs, 1, 0, 0);
        assertEquals("Element removed should be returned", 5,
                deque.removeFirst().intValue());
        assertEquals("Array length shouldn't be changed", 1,
                deque.data.length);
        assertEquals("Size should decrement", 0, deque.size);
        assertEquals("Front shouldnt move", 0, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 0,
                deque.rear);
        assertNull("Index 1 should have been set to null", deque.data[0]);


        Integer[] origs2 = {};
        initDeque(deque, origs2, 0, 0, 0);
        assertNull("Element removed should be returned", deque.removeFirst());
        assertEquals("Array length shouldn't be changed", 0,
                deque.data.length);
        assertEquals("Size should decrement", 0, deque.size);
        assertEquals("Front shouldnt move", 0, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 0,
                deque.rear);

        MyDeque<Integer> deques = new MyDeque<>(10);
        Integer[] finalInt = {5, 3, 4, 12, 11, 10, 9, 8, 7, 6};
        initDeque(deques, finalInt, 10, 2, 1);


        assertEquals("Element removed should be returned", 4,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 12,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 11,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 10,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 9,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 8,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 7,
                deques.removeFirst().intValue());
        assertEquals("Element removed should be returned", 6,
                deques.removeFirst().intValue());
        assertEquals("Size should decrement", 2, deques.size);
        assertEquals("Front shouldnt move", 0, deques.front);
        assertEquals("Rear should not change after calling removeFirst", 1,
                deques.rear);
        assertNull("Index 1 should have been set to null", deques.data[9]);
        assertNull("Index 4 should have been set to null", deques.data[4]);


    }

    @Test
    public void testRemoveLast(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {5, 3, 4, 1, 5, 5, 5, 5, 5, 5};
        initDeque(deque, orig, 10, 2, 1);

        assertEquals("Element removed should be returned", 3,
                deque.removeLast().intValue());
        assertEquals("Array length shouldn't be changed", 10,
                deque.data.length);
        assertEquals("Size should decrement", 9, deque.size);
        assertEquals("Front should move one index after removing from " +
                "non-empty deque", 2, deque.front);
        assertEquals("Rear should change after calling removeFirst", 0,
                deque.rear);
        assertEquals("Index 2 should remain unchanged", 4,
                deque.data[2]);
        assertEquals("Index 3 should remain unchanged", 1,
                deque.data[3]);
        assertNull("Index 1 should have been set to null", deque.data[1]);

        Integer[] origs = {5};
        initDeque(deque, origs, 1, 0, 0);
        assertEquals("Element removed should be returned", 5,
                deque.removeLast().intValue());
        assertEquals("Array length shouldn't be changed", 1,
                deque.data.length);
        assertEquals("Size should decrement", 0, deque.size);
        assertEquals("Front shouldnt move", 0, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 0,
                deque.rear);
        assertNull("Index 1 should have been set to null", deque.data[0]);


        Integer[] origs2 = {};
        initDeque(deque, origs2, 0, 0, 0);
        assertNull("Element removed should be returned", deque.removeLast());
        assertEquals("Array length shouldn't be changed", 0,
                deque.data.length);
        assertEquals("Size should decrement", 0, deque.size);
        assertEquals("Front shouldnt move", 0, deque.front);
        assertEquals("Rear should not change after calling removeFirst", 0,
                deque.rear);

        MyDeque<Integer> deques = new MyDeque<>(10);
        Integer[] finalInt = {5, 3, 4, 12, 11, 10, 9, 8, 7, 6};
        initDeque(deques, finalInt, 10, 2, 1);


        assertEquals("Element removed should be returned", 3,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 5,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 6,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 7,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 8,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 9,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 10,
                deques.removeLast().intValue());
        assertEquals("Element removed should be returned", 11,
                deques.removeLast().intValue());
        assertEquals("Size should decrement", 2, deques.size);
        assertEquals("Front shouldnt move", 2, deques.front);
        assertEquals("Rear should not change after calling removeFirst", 3,
                deques.rear);
        assertNull("Index 1 should have been set to null", deques.data[1]);
        assertNull("Index 5 should have been set to null", deques.data[4]);
    }


 
    /** 
     * Test stack with several elements use of multiple methods 
     */
    @Test
    public void testStackPop() {
        MyStack<Integer> stack = new MyStack<>(9);
        Integer[] notnullArray = {0,1,2,3,4,5,6,7,8};
        initDeque(stack.theStack, notnullArray, 10, 0, 8);
        Integer integer = stack.pop();
        assertEquals(integer, (Integer) 0);
        integer = stack.pop();
        assertEquals(integer, (Integer) 1);
        integer = stack.pop();
        assertEquals(integer, (Integer) 2);
        integer = stack.pop();
        assertEquals(integer, (Integer) 3);
        integer = stack.pop();
        assertEquals(integer, (Integer) 4);
        integer = stack.pop();
        assertEquals(integer, (Integer) 5);
        integer = stack.pop();
        assertEquals(integer, (Integer) 6);
        assertEquals(false, stack.empty());
        integer = stack.pop();
        assertEquals(integer, (Integer) 7);
        assertEquals(false, stack.empty());
        assertEquals(stack.peek(), (Integer) 8);
        integer = stack.pop();
        assertEquals(integer, (Integer) 8);
        integer = stack.pop();
        assertNull(integer);
        assertNull(stack.peek());

        stack = new MyStack<>(10);
        for(int i = 0; i<=9; i++){stack.push((Integer) i);}
        Integer[] notnullArrayTwo = {0,9,8,7,6,5,4,3,2,1};;
        assertArrayEquals(stack.theStack.data, notnullArrayTwo);
        assertEquals(stack.theStack.front, 1);
        assertEquals(stack.theStack.rear, 0);
    }
 
     /**
      * Tests the Queue method. 
      */
     @Test
     public void testQueue() {
        MyQueue<Integer> queue = new MyQueue<>(9);
        Integer[] notnullArray = {0,1,2,3,4,5,6,7,8};
        initDeque(queue.theQueue, notnullArray, 10, 0, 8);
        Integer integer = queue.dequeue();
        assertEquals(integer, (Integer) 0);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 1);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 2);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 3);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 4);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 5);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 6);
        assertEquals(false, queue.empty());
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 7);
        assertEquals(false, queue.empty());
        assertEquals(queue.peek(), (Integer) 8);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 8);
        integer = queue.dequeue();
        assertNull(integer);
        assertNull(queue.dequeue());


        queue = new MyQueue<>(0);
        for(int i = 0; i<=9; i++){queue.enqueue((Integer) i);}
        Integer[] notnullArrayTwo = {0,1,2,3,4,5,6,7,8,9};;
        assertArrayEquals(queue.theQueue.data, notnullArrayTwo);
        assertEquals(queue.theQueue.front, 0);
        assertEquals(queue.theQueue.rear, 9);


        queue = new MyQueue<>(9);
        Integer[] notnullArrayThree = {0,1,2,3,4,5,6,7,8};
        initDeque(queue.theQueue, notnullArrayThree, 10, 0, 8);

        integer = queue.dequeue();
        assertEquals(integer, (Integer) 0);
        integer = queue.dequeue();
        assertEquals(integer, (Integer) 1);
        queue.enqueue(5);
        assertEquals(queue.theQueue.data[0],(Integer) 5);
        queue.enqueue(6);
        assertEquals(queue.theQueue.data[9],(Integer) 6);
        integer = queue.dequeue();
        queue.enqueue(7);
        assertEquals(queue.theQueue.data[9],(Integer) 6);
     }
 






}
