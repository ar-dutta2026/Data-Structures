/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to test the MyLinkedListIterator.java file to make 
 * sure there are no errors 
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;


/**
 * The class MyLinkedListCustomTester essentially tests the mylinkedlist.java
 * in multiples ways to ensure mylinkedlistiterator has no errors 
 * 
 * Instance Variables:
 *  listLen1 - the linkedlist that is populated with 1 string
 *  listLen2 - the linkedlist that is populated with 2 string
 *  ListLen3 - the linkedlist that is not populated (empty)
 *  listLen1Iter - iterator for linkedlist1
 *  listLen2Iter - iterator for linkedlist2
 *  listLen2Iter - iterator for linkedlist3
 */

public class MyListIteratorCustomTester {
    private MyLinkedList listLen1, listLen2,listLen3;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter,
            listLen3Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();

        listLen3 = new MyLinkedList();
        listLen3Iter = listLen3.new MyListIterator();



    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        listLen1Iter.next();
        assertThrows(NoSuchElementException.class, () -> {
            listLen1Iter.next();
        });

    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class, () -> {
            listLen1Iter.previous();
        });

    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        assertThrows(NullPointerException.class, () -> {
            listLen1Iter.add(null);
        });

    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        assertThrows(IllegalStateException.class, () -> {
            listLen3Iter.set("hello");
        });

    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() {
        assertThrows(NullPointerException.class, () -> {
            listLen2Iter.set(null);
        });

    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        assertThrows(IllegalStateException.class, () -> {
            listLen3Iter.remove();
        });

    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.next();
        assertEquals("Tail node should point back to the new node", 
        false, listLen1Iter.hasNext());


    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals("Tail node should point back to the new node", 
        false, listLen1Iter.hasPrevious());

    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals("Tail node should point back to the new node", 
        -1, listLen1Iter.previousIndex());

    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen1Iter.next();
        assertEquals("Tail node should point back to the new node", 
        listLen1.size(), listLen1Iter.nextIndex());

    }
}
