/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a PriorityQueue data structure, with the 
 * same functionality of that of the official java PriorityQueue. 
 */



import java.util.Collection;

/**
 * This class creates a MyPriorityQueue with E extending from Comparable. 
 * It has methods that allow users to push, pop, peek, getLength, and clear
 * 
 * Instance Variables:
 *  heap - the MyMinHeap containing all the objects ordered in priority
 */

public class MyPriorityQueue<E extends Comparable<E>>{
    /**Instance Variables */
    protected MyMinHeap<E> heap;

    /**
     * Constructor that initializes MyPriorityQueue with an empty MyMinHeap
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap();
    }
    /**
     * Constructor that initializes a MyPriorityQueue with a collection of 
     * elements that is then put into MyMinHeap
     * @param collection the elements that we want in MyPriorityQueue
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        //If collection empty or contains null, nullpointerexception is thrown
        if(collection.isEmpty() == true || collection.contains(null)){
            throw new NullPointerException();
        }
        //heap is initialized as a MyMinheap with elements from collection
        heap = new MyMinHeap(collection);
    }

    /**
     * Adds an element to priority queue that heap sorts to its proper position
     * @param element to be added to the priority queue
     */
    public void push(E element){
        //if element is null then nullpointerexception is thrown
        if(element == null){
            throw new NullPointerException();
        }
        //element is inserted into heap
        heap.insert(element);
        return;
    }
    /**
     * Checks the element at beginning of MypriorityQueue (the root of Minheap)
     * @return the root of MyMinHeap
     */
    public E peek(){
        return heap.getMin();

    }
    /**
     * Removes the element from the beginning of PriorityQueue
     * @return the element that is removed
     */
    public E pop(){
        //if heap is empty then null is returned
        if(heap == null){
            return null;
        }
        //root node is removed and returned
        return heap.remove();
    }

    /**
     * The number of elements within heap
     * @return integer size of heap
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Clears all the elements from heap
     */
    public void clear(){
        heap.clear();
    }


    
}
