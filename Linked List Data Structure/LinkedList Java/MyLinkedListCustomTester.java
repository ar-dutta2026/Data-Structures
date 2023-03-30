/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to test the MyLinkedList.java file to make sure 
 * there are no errors 
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The class MyLinkedListCustomTester essentially tests the mylinkedlist.java
 * in multiples ways to ensure mylinkedlist.java has no errors. 
 * 
 * Instance Variables:
 *  emptyIntegerList - the linkedlist that is empty for integers
 *  IntegerList - the linkedlist that contains integer from integers array 
 *  threeStringList - the linkedlist that contains strings from str.data
 *  strData - an array of strings used for testing
 *  integers - an array of integers used for testing. 
 */
public class MyLinkedListCustomTester {
    private MyLinkedList<Integer> emptyIntegerList;
	private MyLinkedList<Integer> IntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = {"Hi", "Greetings", "What's Up!"};
	private int[] integers = {12,24,5,6,5};


	// Optional: add test variables here

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
        emptyIntegerList = new MyLinkedList<Integer>();
		IntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
	}
	/**
	 * This populates the linkedlist with the given nodes. It allows for 
	 * easier testing.
	 */
	private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.threeStringList.new Node(this.strData[0]);
        MyLinkedList<String>.Node node1 =  
            this.threeStringList.new Node(this.strData[1]);
        MyLinkedList<String>.Node node2 =  
            this.threeStringList.new Node(this.strData[2]);

		
		MyLinkedList<Integer>.Node node00 =  
            this.IntegerList.new Node(this.integers[0]);
        MyLinkedList<Integer>.Node node11 =  
            this.IntegerList.new Node(this.integers[1]);
        MyLinkedList<Integer>.Node node22 =  
            this.IntegerList.new Node(this.integers[2]);
		
		

		this.threeStringList.head.next = node0;
		node0.prev = this.threeStringList.head;
		node0.next = node1;
		node1.prev = node0;
		node1.next = node2;
		node2.prev = node1;
		node2.next = this.threeStringList.tail;
		this.threeStringList.tail.prev = node2;
		this.threeStringList.size = 3;
	
		this.IntegerList.head.next = node00;
        node00.prev = this.IntegerList.head;
        node00.next = node11;
        node11.prev = node00;
        node11.next = node22;
        node22.prev = node11;
        node22.next = this.IntegerList.tail;
        this.IntegerList.tail.prev = node22;
        this.IntegerList.size = 3;

    }

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		this.populateLinkedList();
		MyLinkedList<String>.Node oldLastNode = this.threeStringList.tail.prev;
        this.threeStringList.add("Hello");

        assertEquals("Tail node should point back to the new node", 
            "Hello", this.threeStringList.tail.prev.data);
        assertEquals("Previous last node should point to the new added node",
            "Hello", oldLastNode.next.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node previous should be previous last node",
            oldLastNode, this.threeStringList.tail.prev.prev);
        assertSame("New added node next should point to tail",
            this.threeStringList.tail.prev.next, this.threeStringList.tail);
		assertEquals("Returns true?",true, this.threeStringList.add("Hello"));
		

		
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		this.populateLinkedList();
		MyLinkedList<String>.Node oldLastNode = this.threeStringList.head.next;
		this.threeStringList.add(0,"Hello");
		assertEquals("Head node should point back to the new node", 
				"Hello", this.threeStringList.head.next.data);
		assertEquals("Previous node should point to the new added node",
				"Hello", oldLastNode.prev.data);
		assertEquals("Old node should be","Hi", oldLastNode.data );
		assertEquals("Check size is updated", 4, this.threeStringList.size);
		assertSame("Added node previous should be previous last node",
				oldLastNode.prev.prev, this.threeStringList.head);
		assertSame("New added node next should point to nextNode",
				this.threeStringList.head.next.next, oldLastNode);




		// TODO: add your test here
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		this.populateLinkedList();

		MyLinkedList<String>.Node oldLastNode = this.threeStringList.head.next.next;
        this.threeStringList.add(1,"Hello");

        assertEquals("Head node should point back to the new node", 
            "Hello", this.threeStringList.head.next.next.data);
        assertEquals("Previous node should point to the new added node",
            "Hello", oldLastNode.prev.data);
		assertEquals("Old node should be","Greetings", oldLastNode.data );
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node previous should be previous last node",
            oldLastNode.prev.prev, this.threeStringList.head.next);
        assertSame("New added node next should point to nextNode",
            this.threeStringList.head.next.next.next, oldLastNode);

		// TODO: add your test here

	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			emptyIntegerList.remove(0);
			});	

		// TODO: add your test here
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		this.populateLinkedList();
		MyLinkedList<String>.Node node1 = this.threeStringList.head.next;
		this.threeStringList.remove(1);
		assertSame("Previous node should point to the new next element", 
		node1, this.threeStringList.tail.prev.prev);
		assertSame("The new last node should point to tail", 
		node1.next, this.threeStringList.tail.prev);
		assertEquals("new index node should be","Hi", node1.data );
		assertEquals("Size of linked list",
		2, this.threeStringList.size());



		// TODO: add your test here
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
        threeStringList.set(-3, "hello");
        });

	}
}
