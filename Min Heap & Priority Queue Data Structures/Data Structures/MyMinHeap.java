/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a MinHeap data structure, with the same
 * functionality of that of the official java MinHeap. 
 */


import java.util.ArrayList;
import java.util.Collection;

/**
 * This class creates a MyMinHeap, implemented from MinHeapInterface class. 
 * It has methods that allow users to remove, insert, getMinimum element, 
 * and size, and clear list. Along with many protected methods so that it can
 * achieve its purpose.  
 * 
 * Instance Variables:
 *  data - the arraylist containing all the objects
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    /** Magic Numbers */
    private static final int PARENT_ONE = 1;
    private static final int PARENT_TWO = 2;
    private static final int CHILD_TWO = 2;
    private static final int RIGHT_CHILD_DIFFERENTIATOR = 2;
    private static final int LEFT_CHILD_DIFFERENTIATOR = 1;


    /** Instance Variables */
    protected ArrayList<E> data;
    
    /**
     * Constructor that initializes an empty arraylist. 
     */
    public MyMinHeap(){
        data = new ArrayList<E>();
    }

    /**
     * Constructor that intializes MyMinHeap using user inputted collection, 
     * throws null pointer exception if collection or any of its elements
     * are null
     * @param collection of elements that user inputted
     */
    public MyMinHeap(Collection<? extends E> collection){
        //If collection or elements null, then nullpointerexception thrown
        if(collection.isEmpty() == true || collection.contains(null)){
            throw new NullPointerException();
        }
        //initializes an arraylist with elements from collection
        data = new ArrayList(collection);
        //Collection elements are then sorted
       
            //each element is prelocated down to the right position. 
        for(int i = data.size()-1; i>-1; i--){
            this.percolateDown(i);
        }
        
    }	

    /**
     * Swaps two elements with each other
     * @param from index of element to be swapped
     * @param to index that the element will be swapped to
     */
    protected void swap(int from, int to){
        //current element of from
        E elementFrom = data.get(from);
        //current element of to
        E elementTo = data.get(to);
        //sets the index of to with element from from
        data.set(to, elementFrom);
        //sets index of from to element of to
        data.set(from, elementTo);
    }

    /**
     * Gets the parent of the current index. 
     * @param index index of the child
     * @return the integer index of the parent
     */
    protected static int getParentIdx(int index){
        //Uses the parent formula to calculate the index of parent
        int parent = (int) (index - PARENT_ONE)/PARENT_TWO;
        //returns parent index. 
        return parent;
    }

    /**
     * Returns the index of the left child of the parent
     * @param index the index of the parent 
     * @return the integer index of the left child
     */
    protected static int getLeftChildIdx(int index){
        //Uses the leftchild index formula to find the left child index
        int leftChildidx = (CHILD_TWO * index) + LEFT_CHILD_DIFFERENTIATOR;
        //returns leftchild index
        return leftChildidx;
    }

    /**
     * returns the index of the right child of the parent
     * @param index the index of the parent
     * @return the integer index of the right child
     */
    protected static int getRightChildIdx(int index){
        //Uses the right child index formula to find the right child index
        int rightChildidx = (CHILD_TWO * index) + RIGHT_CHILD_DIFFERENTIATOR;
        //returns the right child index
        return rightChildidx;
    }

    /**
     * Returns the the child with the smallest element
     * @param index the index of the parent
     * @return the integer index of the smallest child
     */
    protected int getMinChildIdx(int index){
        //if leftchild doesnt exist then returns -1
        if(getLeftChildIdx(index) >= data.size()){
            return -1;
        }
        //if the right child doesnt exist then left child idx is returned
        else if(getRightChildIdx(index) >= data.size()){
            return getLeftChildIdx(index);
        }
        //if right and left child are equal then left child idx is returned
        else if(data.get(getLeftChildIdx(index)).equals(
            data.get(getRightChildIdx(index)))){
            return getLeftChildIdx(index);
        }
        //if left child smaller than right child then left child is returned
        else if(data.get(getLeftChildIdx(index)).compareTo(
            data.get(getRightChildIdx(index))) < 0){
            return getLeftChildIdx(index);
        }
        //right child is returned if its smaller than left child. 
        else{
            return getRightChildIdx(index);
        }
    }

    /**
     * Percolates an element up until it satisfies all the minheap conditions
     * @param index of the element we are percolating up
     */
    protected void percolateUp(int index){
        //previndex will store the previous index before further modifications
        int prevIndex;
        /**
         * If element at index is less than 0, and index is greater than 0, 
         * element will be perlocated up
         */
        while(data.get(index).compareTo(data.get(getParentIdx(index))) < 0 && 
            index > 0){
            //keeps log of previous index
            prevIndex = index;
            //index is now the parent idx
            index = getParentIdx(prevIndex);
            //elements are swapped
            swap(index,prevIndex);
        }
        return;

    }

    /**
     * Percolates an element down until it satisfies all the minheap conditions
     * @param index of the element that we are percolating down
     */
    protected void percolateDown(int index){
        //previndex will store the prev index before further modifications
        int prevIndex;
        /**
         * If the element at index is greater than 0, and index is less than
         * size of the array, and there are still children, then element is 
         * percolated up
         */
        while(index < data.size() && getMinChildIdx(index) != -1 &&
            data.get(index).compareTo(data.get(getMinChildIdx(index))) > 0){
            //keeps log of previous index
            prevIndex = index;
            //index is now minchild index
            index = getMinChildIdx(prevIndex);
            //elements are swapped
            swap(index,prevIndex);
        }
        return;
    }

    /**
     * Deletes index based off of where the index is. 
     * @param index of the element we want to be deleted
     * @return the element that was deleted
     */
    protected E deleteIndex(int index){
        //the element at the current index
        E element = data.get(index);
        //if element is at end of index, removed and the element is returned
        if(index == data.size() - 1 || this.getMinChildIdx(index) == -1){
            data.remove(index);
            return (E) element;
        }
        //the element in the last index
        E lastElement = data.get((size() -1));
        //the element at the index that is deleted is set to the last element
        data.set(index, lastElement);
        //the element at the end is removed
        data.remove(size()-1);
        //if element is greater than its min child then its percolated down
        //if(data.get(index).compareTo(data.get(getMinChildIdx(index))) >= 0){
            percolateDown(index); 
        //}
        //Percolated up if its not less than min child
       // else{
            percolateUp(index);
       // }
        //returns deleted element
        return (E) element;

    }

    /**
     * Inserts element at end of minheap which is then percolated up
     * @param element that is added to the end of the heap
     */
    public void insert(E element){
        //if element is null, then nullpointer exception is thrown
        if(element == null){
            throw new NullPointerException();
        }
        //element is added to the end of the arraylist
        data.add(element);
        //the element at the end is percolated up
        percolateUp((size()-1));
        return;

    }

    /**
     * Gets the root of the minheap from index 0
     * @returm the root of the min heap
     */
    public E getMin(){
        //Returns null, if minheap is empty
        if(data.isEmpty() == true){
            return null;
        }
        //returns the root of the minheap
        else{
            return (E) data.get(0);
        }

    }

    /**
     * Removes the root of the minheap
     * @return the element that is removed
     */
    public E remove(){
        if(this.size() == 0){
            return null;
        }
        return deleteIndex(0);
    }
    /**
     * Gets the size of the minheap
     * @return the size of minheap
     */
    public int size(){
        return data.size();
    }

    /**
     * Clears the minheap
     */
    public void clear(){
        data.clear();
    }

}
