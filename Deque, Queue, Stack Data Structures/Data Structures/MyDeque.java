/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a Deque data structure, with the same
 * functionality of that of the official java deque. 
 */


/**
 * This class creates a Deque, implemented from the DequeInterface.java class. 
 * It has methods that allow users to remove first, add first, remove last, 
 * and add last, and peekfirst and peek last. Also it can expandCapacity if 
 * needed. 
 * 
 * Instance Variables:
 *  data - the circular array containing all the objected
 *  size - the number of objects in data
 *  rear - the index of the last element of data
 *  front - the index of the first element in data
 */
public class MyDeque<E> implements DequeInterface<E> {
    
    /** Magic Numbers  */
    private static final int MAX_CAPACITY_IF_ZERO = 10;
    private static final int DOUBLE_LENGTH = 2;

    /** Instance Variables */
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor that creates tha deque data structure using an array
     * @param initialCapacity the size of the array containing the data. 
     */
    public MyDeque(int initialCapacity){
        //if initialcapacity less than 0, then illegal arguement
        if(initialCapacity < 0 ){
            throw new IllegalArgumentException();
        }
        //new object array is created, with instance variables set accordingly
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    /**
     * Returns the number of elements in this DequeInterface.
     *
     * @return the number of elements in this DequeInterface
     */
    public int size(){
        return size;
    }

    /**
     * Doubles the capacity of this DequeInterface. If the capacity
     * is 0, set capacity to some default capacity. No elements in this
     * DequeInterface should change after the expansion.
     */
    public void expandCapacity(){
        //creates a new object array that is 2 times length of previous array
        Object[] newArray = new Object[DOUBLE_LENGTH*data.length];
        if(data == null){
            data = new Object[10];
            front = 0;
            rear = 0;
            size = 0;
        }
        //if array length is 0, then sets length to 10. 
        if(data.length == 0 || data == null){
            newArray = new Object[MAX_CAPACITY_IF_ZERO];
            //no data needs to be copied, and sets new array to data
            data = newArray;
            return;
        }
        //sets index to that of front
        int index = front;
        //next is set to 0
        int next = 0;
        //as long as index is not 0, this loop will run. 
        while(index != rear){
            //if index is null, skip this iteration. 
            if(data[index] == null){
                index++;
                continue;
            }
            //sets newArray index to data index. 
            newArray[next] = data[index];
            next++;
            //index is set to 0, if index is at length of array
            if(index+1 == data.length){
                index = 0;
            }
            //index has 1 added to it
            else{
                index++;
            }
        }
        //sets the rear element accordingly 
        newArray[next] = data[rear];
        //front and rear are adjusted for the new array
        front = 0;
        rear = this.size() -1;
        //newArray is set to data. 
        data = newArray;
    }

    /**
     * Adds the specified element to the front of this DequeInterface. If this
     * DequeInterface is at capacity, expandCapacity is called to double the
     * size of this container.
     *
     * @param element the element to add to the front of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addFirst(E element){
        //if element is null, then null pointer exception is thrown
        if(element == null){
            throw new NullPointerException();
        }
        //if the size = length, no space in array, expandCapacity called
        if(size == data.length){
            this.expandCapacity();
        }
        //if size is 0, data[0] is set to element, and front/ size are adjusted
        if(size == 0){
            data[0] = element;
            front = 0;
            size++;
        }
        //if front-1 is not -1 and its empty, then added before current front
        else if( (front-1 != -1) && data[front-1] == null){
            data[front-1] = element;
            front = front - 1;
            //size adjusted accordingly. 
            size++;
        }
        //front is added to end of array with everything adjusted accordingly
        else{
            data[data.length-1] = element;
            front = data.length-1;
            size++;
        }
        return;

    }

    /**
     * Adds the specified element to the back of this DequeInterface. If the
     * DequeInterface is at capacity, expandCapacity is called to double the
     * size of this container.
     *
     * @param element the element to add to the back of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addLast(E element){
        //if element is null, then null pointer exception is thrown. 
        if(element == null){
            throw new NullPointerException();
        }
        //if size == data.length then capacity is expanded, bc array is full
        if(size == data.length){
            this.expandCapacity();
        }
        //if list is empty, then list is added in. 
        if(size == 0){
            data[0] = element;
            rear = 0;
            size++;
        }
        //if space behind rear exists, and empty, then added after curr rear
        else if( (rear+1 != data.length) && data[rear+1] == null){
            data[rear+1] = element;
            rear = rear + 1;
            size++;
        }
        //else its added to the beginning of the array. 
        else{
            data[0] = element;
            rear = 0;
            size++;
        }
        return;



    }


    /**
     * Removes the element at the front of this DequeInterface, and returns the
     * element removed, or null if there was no such element.
     *
     * @return the element removed, or null if there's none.
     */
    public E removeFirst(){
        //returns null, if list is empty
        if(size == 0 || data.length == 0){
            return null;
        }
        //sets element to old element of data
        E element = (E) data[front];
        if(size == 1){
            data[front] = null;
            size--;
            return element;
        }
        //returns element, and removes it from list
        if(front == rear){
            data[front] = null;
            size--;
            return element;
        }
        //if front is at end of array, then front is set to 0
        if((front+1) == data.length){
            data[front] = null;
            front = 0;

        }
        // front becomes element after curr front. 
        else{
            data[front] = null;
            front = front+1;
        }
        //size is substracted
        size--;
        //returns the element. 
        return element;
    }

    /**
     * Removes the element at the back of this DequeInterface, and returns the
     * element removed, or null if there was no such element.
     *
     * @return the element removed, or null if there's none.
     */
    public E removeLast(){
        //returns null if list is empty. 
        if(data.length == 0 || size == 0){
            return null;
        }
        //sets element to old element of data. 
        E element = (E) data[rear];
        //if rear and front are the same it removes element and updates both
        if(size == 1){
            data[rear] = null;
            size--;
            return element;
        }
        if(rear == front){
            data[rear] = null;
            front = 0;
            rear = 0;
            size--;
            return element;
        }
        //removes element
        data[rear] = null;
        //rear is set to the end of the array (bc its circular)
        if((rear-1) == -1){
            rear = data.length-1;
        }
        //rear us set to rear-1 (previous rear)
        else{
            rear = rear-1;
        }
        //size is decremented. 
        size--;
        //returns original element. 
        return element;
    }
    

    /**
     * Returns the element at the front of this DequeInterface,
     * or null if there was no such element.
     *
     * @return the element at the front, or null if there's none.
     */
    public E peekFirst(){
        //if length of array is 0, then null is returned
        if(data.length == 0){
            return null;
        }
        //returns element in the beginning of data (wherever front is)
        return (E) data[front];
    }

    /**
     * Returns the element at the back of this DequeInterface,
     * or null if there was no such element.
     * 
     * @return the element at the back, or null if there's none.
     */
    public E peekLast(){
        //if leneth of array is 0, then null is returned
        if(data.length == 0){
            return null;
        }
        //returns element in the end of data (wherever rear is)
        return (E) data[rear];
    }

}



