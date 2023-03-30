/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a datastructure that is essentially an 
 * LinkedList. It will use all the same functionality that LinkedLists have. 
 */

import java.util.AbstractList;

/**
 * The class MyLinkedList<E> extends the interface AbstractList<E>. 
 * It uses many methods so that it has the functionality of an Linkedlist. 
 * The user can add, add(index), set nodes, remove nodes, clearlist, and get
 * nodes at a certain index. Essentially it is an Linkedlist that we have 
 * implemented directly from Abstractlist. 
 * 
 * Instance Variables:
 *  head - the node head with a value null, will be the first head of the
 * linked list. 
 *  tail - the node tail with a value null, will the last of the linked list. 
 *  size - the size of an arraylist, essentially it is only incremented or
 * decremented when the user decides to add or remove from linkedlist
 */
public class MyLinkedList<E> extends AbstractList<E> {
    //size of linked list
    int size;
    //node head of the linked list
    Node head;
    //node tail of the linked list
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */


    /**
     * Initializes a linkedlist, with dummynodes head and tail set to null
     * and pointing to each other. 
     */
    public MyLinkedList() {
        /* Add your implementation here */
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        size = 0;
        
    }
    /**
     * Returns the integer size of a linkedlist
     * @return integer size of the linkedlist
     */
    @Override
    public int size() {
        return size; 
    }
    /**
     * Gets the data of the node from the desired index.
     * @param index position of where the node is at
     * @return returns the data of the desired node
     */
    @Override
    public E get(int index) {
        //if index is invalid, throws index out of bound exception
        if(index >= size || index<0) {throw new IndexOutOfBoundsException();}
        Node desiredNode = this.head.getNext();
        //returnss data of first index if its 0 
        if(index == 0){return (E) desiredNode.data;}
        //iterates through for-loop to find desired index
        for(int i =1; i<=index;i++){
            desiredNode = desiredNode.getNext();
        }
        return (E) desiredNode.data;
    }

    /**
     * Adds a node at the specified index
     * @param index desired position of where node needs to be added
     * @param data the information (element) that the new node will contain
     */
    @Override
    public void add(int index, E data) {
        //if index is invalid then indexout of bounds
        if(size !=0){
            if(index >size || index<0){throw new IndexOutOfBoundsException();}
        }
        if(size == 0){
            if(index >0 || index<0){throw new IndexOutOfBoundsException();}
        }
        //if data is null, then nulpointerexception is thrown
        if(data == null){throw new NullPointerException();}

        Node addIdx = new Node(data);
        //if index is 0, this executes
        if(index == 0){
            //node reference set to next node after head
            Node nextNode = this.head.getNext();
            //sets next and prev for respective nodes
            this.head.setNext(addIdx);
            nextNode.setPrev(addIdx);
            addIdx.setNext(nextNode);
            addIdx.setPrev(head);
            //size is added
            size++;
            return;
        }
        //if index is at end then this executes
        else if(index == (size)){
            //node referens is set to prev node before tail
            Node previousNode = this.tail.getPrev();
            //sets next and prev for respective nodes
            previousNode.setNext(addIdx);
            addIdx.setPrev(previousNode);
            addIdx.setNext(tail);
            this.tail.setPrev(addIdx);
            //size is added
            size++;
            return;        
        }
        //elsewise it looks to find the desired node at index, and add new node
        else{
            //node ref is set to node after head in preperation for iterating
            //Node desiredNode = head.getNext();
            //searches until hits index, and desiredNode is now accurate
            /*for(int i = 1; i< index;i++){
                desiredNode = desiredNode.getNext();
            }*/

            Node desiredNode = getNth(index);
            //sets next and prev for respective nodes
            addIdx.setNext(desiredNode);
            (desiredNode.prev).setNext(addIdx);
            addIdx.setPrev(desiredNode.prev);
            (desiredNode).setPrev(addIdx);
            //size is added
            size++;
            return;
        }
    }
    /**
     * Adds a node at the end of the linkedlist
     * @param data the information(element) that the new node will contain
     * @return true if add is implemented correctly
     */
    public boolean add(E data) {
        //if data is null, then nullpointer exception will be thrown. 
        if(data == null){
            throw new NullPointerException();
        }
        //creates new node representing what will be adede
        Node addEnd = new Node(data);
        //creaets a new node reference of previous node
        Node previousNode = this.tail.getPrev();
        //sets the node in accordingly with everything else
        this.tail.getPrev().setNext(addEnd);
        addEnd.setPrev(previousNode);
        addEnd.setNext(this.tail);
        this.tail.setPrev(addEnd);
        //size is added
        size++;
        //true is returns if this was possinble
        return true;
    }

    /**
     * @param index the position of the node that reqs to have value changed
     * @param data the element that will be assigned to the index node
     * @return the data of the old node
     */
    public E set(int index, E data) {
        //invalid index then throws index out of bounds exception
        if(size !=0){
            if(index >=size || index<0){throw new IndexOutOfBoundsException();}
        }
        if(size == 0){
            if(index >0 || index<0){throw new IndexOutOfBoundsException();}
        }
        //if data is null, then throws nullpointerexception
        if(data == null){throw new NullPointerException();}
        //current node set to desired node
        Node currentNode = getNth(index);
        //old data is set to the data of the current node
        E oldData = currentNode.data;
        //data is changed to new data
        currentNode.setElement(data);
        //old data is returned.
        return (E) oldData; 
    }
    
    /**
     * This will remove the node at the desired place, 
     * @param index the position of the node that is suppose to be removed
     */
    public E remove(int index) {
        //if index is invalid then it will throw indexoutofboundsexception
        if(size !=0){
            if(index >=size || index<0){throw new IndexOutOfBoundsException();}
        }
        if(size == 0){
            if(index >0 || index<0){throw new IndexOutOfBoundsException();}
        }
        //finds the node that need to be removed. 
        Node currentNode = getNth(index);
        //if index is at 0 then this will excecute
        if(index == 0){
            //reassigns head and respective node
            Node nextNode = head.getNext().getNext();
            nextNode.setPrev(head);
            head.setNext(nextNode);
            //size is substracted because it was removed
            size--;
            return (E) currentNode.data;
        }
        //if index is at end then this will excecture
        else if(index == size-1){
            //reassigns tail and other nodes
            Node previousNode = tail.getPrev().getPrev();
            previousNode.setNext(tail);
            tail.setPrev(previousNode);
            //size is substracted because it was removed
            size--;
            return (E) currentNode.data;
        }
        //ressaigns next and prev of the nodes around the node that is removed
        else{
            currentNode.getPrev().setNext(currentNode.getNext());
            currentNode.getNext().setPrev(currentNode.getPrev());
            //takes away from size
            size--;
            //returns data of old node
            return (E) currentNode.data;
        }
    }

    /**
     * Clears the linkedlist, and resets size to 0, and resets where head and 
     * tail points towards (so they point to each other)
     */
    public void clear() {
        this.head.setNext(tail);
        this.tail.setPrev(head);
        size = 0;
    }
    /**
     * Checks to see if linkedlist is empty
     * @return true if linkedlist is empty, false if not
     */
    public boolean isEmpty() {
        if(size == 0){return true;}
        return false;
    }

    /**
     * Will get the desired node based off of the index
     * @param index the position where the desired node is in
     * @return the node at the desired index
     */
    protected Node getNth(int index) {
        //if index is invalid then indexoutofboundsexception is thrown
        if(index >= size || index<0) {throw new IndexOutOfBoundsException();}
        Node desiredNode = this.head.getNext();
        //if index is at 0 then returns first node
        if(index == 0){return (Node) desiredNode;}
        //loops through to find the desired node.
        for(int i =1; i<=index;i++){
            desiredNode = desiredNode.getNext();
        }
        //returns desired node
        return (Node) desiredNode;
    }
    
}
