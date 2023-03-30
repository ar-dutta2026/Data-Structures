/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a datastructure that is essentially an 
 * ArrayList. It will use all the same functionality that ArrayLists have. 
 */

/**
 * The class MyArrayList<E> implements the interface MyList<E>. It uses many 
 * methods so that it has the functionality of an Arraylist. The user can 
 * prepend, append, insert elements, remove elements, increase capacity, get 
 * elements at a certain index. Essentially it is an Arraylist that we have 
 * implemented directly from MyList. 
 * Instance Variables:
 *  data - the array that we can modify and the user will be essentially use
 * as an arraylist, it will constantly be edited as elements will be appended
 * prepended, inserted, removed, etc. 
 *  size - the size of an arraylist, essentially it is only incremented or
 * decremented when the user decides to add or remove from data obj array
 * 
 */
public class MyArrayList<E> implements MyList<E>{
    //initial capacity of any arraylist
    static final int INITIAL_CAPACITY = 5;
    //If capacity is reached (or close to it), then three will be added
    static final int ADD_THREE_CAPACITY = 3;
    //the capacity of an array if its empty
    static final int ZERO_CAPACITY = 0;
    //Will be the object array used as ArrayList (store all object data list)
    Object[] data;
    //the size of an array (how much user puts and removes) NOT CAPACITY
    int size;

    /**
     * Initiialize and creates an object MyArrayList with set length of 5
     */
    public MyArrayList(){
        this.data = new Object[INITIAL_CAPACITY];
    }

    /**
     * Initializes and creates an object array MyArrayList with set length of
     * initial capacity
     * @param initialCapacity used to determine the capacity of MyArrayList
     */
    public MyArrayList(int initialCapacity){
        //throws exception if negative
        if(initialCapacity <ZERO_CAPACITY){
            throw new IllegalArgumentException();
        }
        //Creates an object array MyArrayList with set length of init capacity
        else{
            this.data = new Object[initialCapacity];
        }
    }

    /**
     * Creates a shadow copy of an arraylist with an object MyArrayList
     * @param arr the array list that is shadow copied from. 
     */
    public MyArrayList (E[] arr){
        //If array is null then it initializes MyArraylist with length 5
        if(arr == null){
            this.data = new Object[INITIAL_CAPACITY];
        }
        //It will initialize object MyArrayList based on the input array
        else{
            this.data = arr;
            size = arr.length;
        }
        
    }


    /**
     * Increase the capacity of underlying array if needed
     *
     * @param requiredCapacity minimum capacity after expanding
     */
    public void expandCapacity(int requiredCapacity){
        /*
         * If required Capacity is less than required capacity
         */
        if(requiredCapacity<this.getCapacity()){
            throw new IllegalArgumentException();
        }
        /*
         * If current capacity is 0 and required capacity exceeds 
         * initial capacity value (5), then an array with required capacity is
         * created
         */

        else if(this.getCapacity() == ZERO_CAPACITY && 
           requiredCapacity > INITIAL_CAPACITY){
            this.data = new Object[requiredCapacity];
        }
        /**
         * If current capacity is 0 and required capacity does not exceed 
         * initial capacity length (5) we set current capacity to 5
         */
        else if(this.getCapacity() == ZERO_CAPACITY && 
                requiredCapacity<=INITIAL_CAPACITY){
            this.data = new Object[INITIAL_CAPACITY];
        }
        /**
         * If current capacity+3 is not greater than required capacity we 
         * create a deep copy of an array. with the new capacity length of
         * required capacity
         */
        else if((this.getCapacity()+ADD_THREE_CAPACITY)<=requiredCapacity){
            Object[] copy = new Object[requiredCapacity];
            for(int i =0;i<data.length;i++){
                copy[i] = data[i];
            }
            this.data = copy;
        }
        /**
         * If current capacity+3 is  greater than required capacity we 
         * create a deep copy of an array. with the new capacity length of
         * old capacity+3
         */

        else if((this.getCapacity()+ADD_THREE_CAPACITY)>=requiredCapacity){
            Object[] copy = new Object[this.getCapacity()+ADD_THREE_CAPACITY];
            for(int i =0;i<data.length;i++){
                copy[i] = data[i];
            }
            this.data = copy;
        }
        /**
         * if none of these conditions are met then an illegalargumentexception
         * is thrown
         */
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Get the amount of elements array can hold
     *
     * @return number of elements that can be held
     */
    public int getCapacity(){
        //returns length of Object array data
        return this.data.length;
    }

    /**
     * Add an element at the specified index
     *
     * @param index   position to insert the element
     * @param element the element to insert
     */
    public void insert(int index, E element){
        //Checks if index is valid, throws indexoutofboundsexception if not
        if(index<0 || index>this.size()){
            throw new IndexOutOfBoundsException();
        }
        //expands capacity if size+1 of the array is greater than the capacity
        if((this.size()+1)>=getCapacity()){
            this.expandCapacity(getCapacity()+1);
        }
        //will be the new array after element is inserted
        Object[] copy = new Object[this.getCapacity()];
        //creates a deep copy of the array until index is reached
        for(int i = 0;i<data.length;i++){
            //if array is index then breaks from loop
            if(i == index){
                copy[i] = element;
                break;
            }
            copy[i] = data[i];
        }
        //To refer to the place the new array is at after element is inserted
        int placeholder = index+1;
        //if index is at beginning this will execute (to avoid out of bounds)
        if(index == 0){
            //copies from right after index 0
            for(int i = index;i<this.size();i++){
                copy[placeholder] = data[i];
                placeholder++;
            }
            //the data array is now the new array
            this.data = copy;
            //size is incremented
            size++;
            return;
        }
        //copies the rest of the array remaining after index
        for(int i = index;i<(this.size);i++){
            copy[placeholder] = data[i-1];
            placeholder++;
        }
        //the data array is now the new array
        this.data = copy;
        //size is incremented
        size++;
    }


    /**
     * Add an element to the end of the list
     *
     * @param element the element to append
     */
    public void append(E element){
        //uses insert to add an element to the end of the MyArrayList size
        if(element == null){
            if((this.size()+1)>=getCapacity()){
                this.expandCapacity(getCapacity()+1);
            }
            Object[] copy = new Object[this.getCapacity()];
            copy[size] = null;
            for(int i = 0;i<data.length;i++){
                //if array is index then breaks from loop
                copy[i] = data[i];
            }
            this.data = copy;
            size++;
            return;
        }
        else{
        this.insert((size), element);
        }

    }


    /**
     * Add an element to the beginning of the list 
     *
     * @param element the element to prepend
     */
    public void prepend(E element){
        //uses insert to add an element to the beginningof MyArrayList
         
        if(element == null){
            if((this.size()+1)>=getCapacity()){
                this.expandCapacity(getCapacity()+1);
            }
            Object[] copy = new Object[this.getCapacity()];
            copy[0] = null;
            int placeholder = 1;
            for(int i = 0;i<(this.size);i++){
                copy[placeholder] = data[i];
                placeholder++;
            }
            this.data = copy;
            size++;
            return;
        }
        else{
        this.insert(0, element);
        }

    }

    /**
     * Get the element at the given index
     *
     * @param index the index at which to return the element
     * @return the element at the index
     */
    @SuppressWarnings("unchecked")
    public E get(int index){
        //Checks if index is valid, throws indexoutofboundsexception if not
        if(index>=this.size() || index<0){
            throw new IndexOutOfBoundsException();
        }
        //returns the element at index
        return (E) data[index];
    }

    /**
     * Replaces an element at the specified index with a new element and return
     * the original element
     *
     * @param index   the index at which to replace
     * @param element the element with which to replace
     * @return the original element
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element){
        //Checks if index is valid, throws indexoutofboundsexception if not
        if(index>=this.size() || index<0){
            throw new IndexOutOfBoundsException();
        }
        //copies the reference of the original element to return in the end
        E originalElement = (E) data[index];
        //Adds the new element to the given position
        data[index] = element;
        //returns original element
        return (E) originalElement;
    }

    /**
     * Remove the element at the specified index and return the removed element
     *
     * @param index the index at which to remove the element
     * @return the removed element
     */
    @SuppressWarnings("unchecked")
    public E remove(int index){
        //Checks if index is valid, throws indexoutofboundsexception if not
        if(index>=this.size() || index<0){
            throw new IndexOutOfBoundsException();
        }
        //copies the reference of the original element to return in the end
        E originalElement = (E) data[index];
        //creates the new array that will be data after element is removed
        Object[] copy = new Object[this.getCapacity()];
        //creates deep copy of an array
        for(int i = 0;i<data.length;i++){
            //once reached given index loop breaks
            if(i == index){
                break;
            }
            copy[i] = data[i];
        }
        //starts off from after index, and copies the rest of data array
        for(int i = index;i<(data.length-1);i++){
            copy[i] = data[i+1];
        }
        //data array is now the new array formed using this method
        this.data = copy;
        //substract from size since we removed an element
        size--;
         //returns original element
        return (E) originalElement;
    }

    /**
     * Get the number of elements in the list
     *
     * @return number of elements in the list
     */
    public int size(){
        //returns size of the data array
        return size;
    }

}