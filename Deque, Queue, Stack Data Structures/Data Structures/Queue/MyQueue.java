/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a Queue data structure, with the same
 * functionality of that of the official java Queue. 
 */

/**
 * This class creates a Queue, implemented from the StackInterface.java class. 
 * It has methods that allow users to enqueue, dequeue, peek, check size, and 
 * check if empty or not. It is implemented using the deque data structure. 
 * 
 * Instance Variables:
 *  theStack - the deque data structure underlying the Stack
 */
public class MyQueue<E> implements QueueInterface<E> {
    MyDeque<E> theQueue;

    /**
     * Constructor to create new MyQueue that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyQueue(int initialCapacity) {
        //creates a new Queue by initializing a deque data structure
        theQueue = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if there are no elements in the queue, false otherwise.
     */
    @Override
    public boolean empty() {
        //if there are no objects in theQueue returns true
        if( theQueue.size == 0){
            return true;
        }
        //if there is some object, then returns false
        else{
            return false;
        }
    }

    /**
     * Adds the specified element to the tail of this MyQueue.
     *
     * @param element the element to add to the queue
     */
    @Override
    public void enqueue(E element) {
        //adds an element to the end of Queue
        theQueue.addLast(element);
    }

    /**
     * Removes the element at the head of this MyQueue.
     * Returns the element removed, or null if there was no such
     * element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E dequeue() {
        //removes teh first element of Queue
        return theQueue.removeFirst();
    }

    /**
     * Returns the element at the head of this MyQueue,
     * or null if there was no such element.
     *
     * @return the element at the head, or null if the size was zero.
     */
    @Override
    public E peek() {
        //checks the first element of Queue
        return (E) theQueue.peekFirst();
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        //returns how many elements in is Queue
        return theQueue.size();
    }
}
