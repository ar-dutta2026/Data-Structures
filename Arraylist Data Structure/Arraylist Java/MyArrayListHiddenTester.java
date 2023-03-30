/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file contains all the hidden tests. Essentially I thoroughly test my
 * MyArrayList using junit 
 *  
 */

import static org.junit.Assert.*;

import org.junit.*;
/**
 * This class creates a test fixture and runs multiple tests on 
 * your implementation for MyArrayList.  
 * 
 * Instance variables:
 * arr - Object array that will be used to set up the test fixture
 * arrInts - Integer array that will be used to set up the test fixture
 * arrNonEmptyInts - Integer array that will be used to be mix of null and ints
 * ListRemoveArray - Integer array that will be used to remove elements
 */
public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] arr = new Object[10];
    Integer[] arrInts = { 1, 2, 3 };
    Integer[] arrNonEmptyInts = {1, null, 2, null}; // NOTE: LIST OF SIZE ONE
    Integer[] listRemoveArray = {1,2,3,4,5,6};

    private MyArrayList listEmpty, listNonEmpty, listDefaultCap, 
    listCustomCapacity, listWithNull, listWithInt, listInvalid, 
    listNull,listArrayWithNull,listRemove;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listArrayWithNull = new MyArrayList<>(arrNonEmptyInts);
        //listNonEmpty.size = 1;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(arr);
        listWithInt = new MyArrayList<Integer>(arrInts);
        listNull = new MyArrayList<Integer>(null);
        listRemove = new MyArrayList<Integer>(listRemoveArray);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        assertThrows("Check for Exception",
        IllegalArgumentException.class, 
        ()-> new MyArrayList<Integer>(-1));
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        assertEquals(
            "Check size for the constructor with list argument",
                0, listNull.size());
        assertEquals(
            "Check capacity for the constructor with list argument",
                5, listNull.getCapacity());
        assertArrayEquals("Check data", new Integer[5], listNull.data);



    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        assertEquals(
            "Check size for the constructor with list argument",
        4, listArrayWithNull.size());
        assertEquals(
            "Check capacity for the constructor with list argument",
        4, listArrayWithNull.getCapacity());
        assertArrayEquals(
            "Check data", new Integer[]{1, null, 2, null}, 
            listArrayWithNull.data);

    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listWithInt.append(4);

        assertArrayEquals("Check for successful append", 
        new Integer[]{1, 2, 3, 4,null,null}, listWithInt.data);
        assertEquals("Check list size after the append",
                4, listWithInt.size);
        assertEquals("Check Capacity after the append", 
        6,listWithInt.getCapacity());
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listWithInt.append(null);

        assertArrayEquals("Check for successful append", 
        new Integer[]{1, 2, 3, null,null,null}, listWithInt.data);
        assertEquals("Check list size after the append",
                4, listWithInt.size);
        assertEquals("Check Capacity after the append", 
        6,listWithInt.getCapacity());
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listWithInt.prepend(4);

        assertArrayEquals("Check for successful append", 
        new Integer[]{4,1, 2, 3,null,null}, listWithInt.data);
        assertEquals("Check list size after the append",
                4, listWithInt.size);
        assertEquals("Check Capacity after the append", 
        6,listWithInt.getCapacity());
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listWithInt.prepend(null);

        assertArrayEquals("Check for successful prepend", 
        new Integer[]{null,1, 2, 3, null,null}, listWithInt.data);
        assertEquals("Check list size after the prepend",
                4, listWithInt.size);
        assertEquals("Check Capacity after the prepend", 
        6,listWithInt.getCapacity());

    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBound(){
        assertThrows("Check for Exception",
        IndexOutOfBoundsException.class, 
        ()-> listWithInt.insert(-1,5));


    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for(int i =0; i<1000;i++){
            listNull.insert(i, i);
        }

        assertEquals("Check size:",1000, listNull.size);
        assertEquals(
            "Check capacity:",1001, listNull.getCapacity());

    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        assertThrows("Check for Exception",
        IndexOutOfBoundsException.class, ()-> listWithInt.get(50));

    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        assertThrows("Check for Exception",
        IndexOutOfBoundsException.class, ()-> listWithInt.set(-1,2));

    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        listRemove.remove(5);
        listRemove.remove(2);
        listRemove.remove(1);

        assertEquals("Check size:",3, listRemove.size);
        assertEquals("Check capacity:",6, 
        listRemove.getCapacity());
        assertArrayEquals("Check for successful remove", 
        new Integer[]{1,4,5,null,null,null}, listRemove.data);
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        assertThrows("Check for Exception",
        IndexOutOfBoundsException.class, ()-> listWithInt.remove(-1));
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
    assertThrows("Check for Exception",
    IllegalArgumentException.class, ()-> listWithInt.expandCapacity(-1));

    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        listRemove.expandCapacity(100);
        assertEquals("Chack Capacity",100,
        listRemove.getCapacity());
    }
    

}
