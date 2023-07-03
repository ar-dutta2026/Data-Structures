/**
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources used: Lecture Notes
 * 
 * This file's purpose is to create a BST data structure, with the same
 * functionality of that of the official java BST. 
 */


import java.util.ArrayList;

/**
 * This class creates a BST. It has methods that allow a user to insert nodes, 
 * remove nodes, search for nodes, and create an arraylist of the nodes in 
 * succession order. It also has the class to create the nodes itself
 * 
 * Instance Variables:
 *  root - the node that has no parents. Top of tree
 *  size - the number of nodes in the BST
 */
public class MyBST<K extends Comparable<K>, V> {
    /**Instance Variables */
    MyBSTNode<K, V> root = null;
    int size = 0;
    /**
     * The size of an array, as an integer
     * @return the integer size of the array.
     */
    public int size() {
        return size;
    }

    /**
     * Inserts a node into the BST. If there is a duplicate, value is replaced.
     * @param key of the inserted node that is used to find where to place it
     * @param value of the node, this will also be returned
     * @return the value of the previous node before insertion or null
     */
    public V insert(K key, V value) {
        //if key is null then nullpointer excpetion ins thrown
        if(key == null){
            throw new NullPointerException();
        }
        //if there is no root, then root is inserted. 
        if(root == null){
            root = new MyBSTNode(key, value, null);
            size++;
            return null;
        }
        //found will be used to verify if placement is found
        boolean found = false;
        //sets new MyBSTNode variable to root
        MyBSTNode<K, V> node = root;
        /*
         * Essentially, until an empty spot, or duplicate is found, it 
         * will loop thru comparing the key values accordingly until
         * it finds an empty spot. 
         */
        while(found == false){
            //if the key already exists, value is replaced, ogValue returned
            if(key.compareTo(node.getKey()) == 0){
                found = true;
                V valueOG = node.getValue();
                node.setValue(value);
                return valueOG;
            }
            //if comparison is greater than 0, then it goes to the right
            else if(key.compareTo(node.getKey()) > 0){
                //if right is empty, then node is added to the right
                if(node.getRight() == null){
                    found = true;
                    //newNode is created to be added
                    MyBSTNode<K, V> newNode = new MyBSTNode(key, value, node);
                    node.setRight(newNode);
                    //size is modified
                    size++;
                    //null is returned
                    return null;                
                }
                //loop continues, and the node is assigned as right. 
                else{
                    node = node.getRight();
                }
            }
            //if comparison is less than 0, then it goes to the left
            else if(key.compareTo(node.getKey()) < 0){
                //if left is empty then node is added to the left
                if(node.getLeft() == null){
                    found = true;
                    //newnode is created to be added
                    MyBSTNode<K, V> newNode = new MyBSTNode(key, value, node);
                    node.setLeft(newNode);
                    //size is modified
                    size++;
                    //null us returned
                    return null;   
                }
                //loop continues and the node is assigned to the right
                else{
                    node = node.getLeft();
                }
            }
        }
        //returns null, bc insertion was possible
        return null;
    }

    /**
     * Searches for the respective node based on the key
     * @param key K that is used to identify the node we are searching for
     * @return the value of the node, or null if empty
     */
    public V search(K key) {
        //if key is null, then null is returned
        if(key == null){
            return null;
        }
        //
        boolean found = false;
        //new node is assigned to root
        MyBSTNode<K, V> node = root;
        
        /**
         * Searches for the node that is from the respective key
         */
        while(found == false){
            //returns the value of node if key is found
            if(key.compareTo(node.getKey()) == 0){
                return node.getValue();
            }
            //if graeter continues the loop
            else if(key.compareTo(node.getKey()) > 0){
                    node = node.getRight();
            }
            //if lesser then continues the loop
            else if(key.compareTo(node.getKey()) < 0){
                    node = node.getLeft();
            }
            //if node is null at any point then that is returned
            if(node == null){
                return null;
            }
            
        }
        //null is returned if no key is found
        return null;
    }

    /**
     * Removes the node based off of key, adjusts BST accordingly.  
     * @param key of the node that will be removed
     * @return the value of the node that is removed. 
     */
    public V remove(K key) {
        //if key is null, then null is returned (nothing is removed)
        if(key == null){
            return null;
        }
        
        boolean found = false;
        MyBSTNode<K, V> node = root;
        /**
         * Searches for the  to be removed node from the respective key. 
         */
        while(found == false){
            //returns the current node if key is found
            if(key.compareTo(node.getKey()) == 0){
                found = true;
            }
            //if greater key goes to the right
            else if(key.compareTo(node.getKey()) > 0){
                    node = node.getRight();
            }
            //if lesser key goes ot the left
            else if(key.compareTo(node.getKey()) < 0){
                    node = node.getLeft();
            }
            //if node is still null (doesnt exist) then null is returned
            if(node == null){
                return null;
            }
        }

        //if node is still null (doesnt exist) then null is returned
        if(node == null){
            return null;
        }
        //size is decremented
        size--;
        //if both children are null, then we remove node, and reset parent
        if(node.getLeft() == null && node.getRight() == null){
            //checks to see if it is a left child relationship with parent
            if(node.getParent() != null && node.getParent().getLeft() == node){
                node.getParent().setLeft(null);
            }
            //checks to see if its a right child relationship with parent
            if(node.getParent() != null && node.getParent().getRight()== node){
                node.getParent().setRight(null);
            }
            if(node == root){
                root = null;
            }
            node.setParent(null);
            return node.getValue();
        }
        /*
         * if left child is null, move right child up, set everythingelse 
         * accordingly
         */
        else if(node.getLeft() == null) {
            //checks to see if it is a left child relationship with parent
            if(node.getParent() != null && node.getParent().getLeft() == node){
                node.getParent().setLeft(node.getRight());
            }
            //checks to see if its a right child relationship with parent
            if(node.getParent() != null && node.getParent().getRight() == node){
                node.getParent().setRight(node.getRight());
            }
            //sets everything accordingly
            if(node == root){
                root = node.getRight();
            }
            node.getRight().setParent(node.getParent());
            node.setParent(null);
            node.setRight(null);
            //returns old node value
            return node.getValue();
        }
        /*
         * if right child is null, we move left child up and set everything 
         * else accordignly. 
         */
        else if(node.getRight() == null) {
            //checks to see if its a left child relationship with parent
            if(node.getParent() != null && node.getParent().getLeft() == node){
                node.getParent().setLeft(node.getLeft());
            }
            //checks to see if its a left child relationship with parent
            if(node.getParent() != null && node.getParent().getRight() == node){
                node.getParent().setRight(node.getLeft());
            }
            //sets everything accordingly
            if(node == root){
               root = node.getLeft();
            }
            node.getLeft().setParent(node.getParent());
            node.setParent(null);
            node.setLeft(null);
            //returns old node value
            return node.getValue();
        }
        /*
         * We replace the removed node with the successor and we adjust 
         * everything as needed. 
         */
        else{
            MyBSTNode<K,V> newNode = node.successor();
            if(node == root){
                root = newNode;
            }
            //If node successor is the left of new node it adjusts accordingly
            if(node.getLeft() == newNode){
                //if left left node is null then adjusts accordingly
                if(node.getLeft().getLeft() == null){
                    newNode.setLeft(node.getLeft().getRight());
                    node.getLeft().getRight().setParent(newNode);
                    node.getRight().setParent(newNode);
                    newNode.setRight(node.getRight());
                }
                //if left right node is null then adjusts accordingly. 
                else{
                    newNode.setLeft(node.getLeft().getLeft());
                    node.getLeft().getLeft().setParent(newNode);
                    node.getRight().setParent(newNode);
                    newNode.setRight(node.getRight());


                }
            }
            //if not successor is the right of the newnode adjusts accordingly
            else if(node.getRight() == newNode){
                //if right right node is null then adjusts accordingly
                if(node.getRight().getRight() == null){
                    newNode.setRight(node.getRight().getLeft());
                    node.getRight().getLeft().setParent(newNode);
                    node.getLeft().setParent(newNode);
                    newNode.setLeft(node.getLeft());
                }
                //if right left node is null then adjusts accordingly
                else{
                    newNode.setRight(node.getRight().getRight());
                    node.getRight().getRight().setParent(newNode);
                    node.getLeft().setParent(newNode);
                    newNode.setLeft(node.getLeft());

                }
            }
            //if successor is somewhere else it adjusts accordingly
            else{
                //sets everything accordingly with parent. 
                node.getLeft().setParent(newNode);
                node.getRight().setParent(newNode);
                newNode.setLeft(node.getLeft());
                newNode.setRight(node.getRight());
            }
            //checks to see if its a left child relationship with parent
            if(node.getParent() != null && node.getParent().getLeft() == node){
                node.getParent().setLeft(newNode);
            }
            //checks to see if its a right child relationship with parent
            if(node.getParent() != null && node.getParent().getRight() == node){
                node.getParent().setRight(newNode);
            }
            //sets the parent
            newNode.setParent(node.getParent());
            //returns the value of the original node
            return node.getValue();
        }
    }

    /**
     * Creates an arraylist of nodes based off of succession order
     * @return an arraylist of nodes, that is in order
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        //Creates an arraylist of nodes
        ArrayList<MyBSTNode<K, V>> arrayList = new ArrayList<MyBSTNode<K, V>>();
        //if root is null then empty arraylist is returned
        if(root == null){
            return arrayList;
        }
        //assigns root to node
        MyBSTNode<K, V> node = root;
        /*
         * Goes down to the node with the lowest value
         */
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        /*
         * Uses sucession traversal to go through all nodes in BST. Adds them
         * to the node arraylist
         */
        while(node != null){
            arrayList.add(node);
            node = node.successor();
        }
        //returns the arraylist full of nodes. 
        return arrayList;
    }
    /**
     * This class creates a MyNode for the Binary Search Tree, and uses
     * a bunch of methods to make sure nodes have relation to each other. 
     * Instance Variables: 
     * key - the key (identifier) of the node
     * value - the value stored in the node
     * parent - the nodes parent (points to it, and it points to)
     * left - the left node that is points to
     * right - thr right node it points to. 
     */
    static class MyBSTNode<K, V> {
        /**Magic Numbers */
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";
        /** Instance Variables */
        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode<
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Finds the successor node to the current node
         * @return the successor of the current node. 
         */
        public MyBSTNode<K, V> successor() {
            //sets placeholder to right node
            MyBSTNode<K, V> placeholder = this.getRight();
            //if its not null, then this will run
            if(placeholder != null){
                //will traverse as many left nodes it can, finding successor
                while(placeholder.getLeft() != null){
                    placeholder = placeholder.getLeft();
                }
                //returns the successor
                return placeholder;
            }
            //If the node has no right node then this is run
            else{
                //gets the parent node
                placeholder = this.getParent();
                //sets the node to current node
                MyBSTNode<K,V> node = this;
                //condition for loop
                boolean check = false;
                //will continuously get parent until everything else works
                while(check == false){
                    /* 
                     * loop will stop when placeholder null or the right of
                     * node is placeholder
                     */
                    if(placeholder == null || node != placeholder.getRight()){
                        check = true;
                    }
                    //placeholder becomes parent as it continues to traverse up
                    else{
                        node = placeholder;
                        placeholder = placeholder.getParent();
                    }
                }
                //successor node returned once it is found. 
                return placeholder;
            }


        }
                

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
