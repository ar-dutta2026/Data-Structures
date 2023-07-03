/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a Stack data structure, with the same
 * functionality of that of the official java Queue. 
 */


/**
 * This class creates a Stack, implemented from the StackInterface.java class. 
 * It has methods that allow users to pop, push, peek, check size, and if check
 * if empty or not. It is implemented using the deque data structure. 
 * 
 * Instance Variables:
 *  theStack - the deque data structure underlying the Stack
 */
public class MyStack<E> implements StackInterface<E> {

    /** Instance Variables */
    MyDeque<E> theStack;

    /**
     * Constructor to create new MyStack that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyStack(int initialCapacity) {
        //creates new myStack
        theStack = new MyDeque<E>(initialCapacity);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if there are no elements in the stack, false otherwise.
     */
    @Override
    public boolean empty() {
        //if stack has no objects, then true is returned
        if(theStack.size == 0){
            return true;
        }
        //if stack is not empty then false is returned. 
        else{
            return false;
        }
    }

    /**
     * Adds the specified element to the top of this Stack.
     *
     * @param element the element to add to the stack
     */
    @Override
    public void push(E element) {
        //adds an element to the beginning of Deque Structure
        theStack.addFirst( (E) element);
    }

    /**
     * Removes the element at the top of this Stack.
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E pop() {
        //removes the element from top of deque, and returns the old element
        return (E) theStack.removeFirst();
    }

    /**
     * Returns the element at the top of this stack, or null if there was no
     * such element.
     *
     * @return the element at the top, or null if the size was zero
     */
    @Override
    public E peek() {
        //checks the element in the beginning of deque
        return theStack.peekFirst();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack.
     */
    @Override
    public int size() {
        //returns the size of the stack. 
        return theStack.size();
    }

}
