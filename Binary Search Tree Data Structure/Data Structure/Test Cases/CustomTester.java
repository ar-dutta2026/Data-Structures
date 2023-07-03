/*
 * Name: Aritra Dutta
 * Email: ardutta@ucsd.edu
 * PID: A17685487
 * Sources Used: Lecture Notes, Zybooks, PublicTester.java
 *
 * This file is the custom tester for CSE 12 PA8 in Winter 2023.
 * It contains tests from methods from the BST 
 * to ensure that there are no errors. It uses JUnit tests. 
 */




import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * The class CustomTester essentially tests the BST
 * in multiple ways to ensure that they have no errors. 
 * Instance Variables: 
 * tree - a basic BST
 * oneNode - has only oneNode
 * oneNode2 - another version of oneNode
 */
public class CustomTester {
    MyBST<Integer, Integer> tree;
    MyBST<Integer, Integer> oneNode;
    MyBST<Integer, Integer> oneNode2;



    /**
     * Setup for one node and tree. 
     */
    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);
        MyBST.MyBSTNode<Integer, Integer> root2 =
                new MyBST.MyBSTNode<>(4, 1, null);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;

        this.oneNode = new MyBST<>();
        this.oneNode.root = root2;


        
    }

    /**
     * Tests the successor method thoroughly
     */
    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(treeRoot.getRight().getLeft(), treeRoot.successor());
        assertNull(oneNode.root.successor());

        MyBST.MyBSTNode<Integer, Integer> root2 =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> rightNode =
                new MyBST.MyBSTNode<>(5, 1, root2); 
        MyBST.MyBSTNode<Integer, Integer> rightNode2 =
                new MyBST.MyBSTNode<>(6, 1, rightNode); 

        this.oneNode = new MyBST<>();
        this.oneNode.root = root2;
        root2.setRight(rightNode);
        rightNode.setRight(rightNode2);
        assertSame(oneNode.root.getRight().getRight(), rightNode.successor());
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);
        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
        assertSame(root,three.successor());
        assertSame(two,one.successor());
        assertNull(six.successor());

    }



    /**
     * Tests the insert method thoroughly
     */
    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root2 = tree.root;
        tree.insert(10, 10);
        assertEquals(10, root2.getRight().getRight().getKey().intValue());
        assertSame(root2.getRight(), root2.getRight().getRight().getParent());
        assertEquals("size of tree", 7, tree.size);
        this.oneNode = new MyBST<>();
        oneNode.insert(7,1);
        assertEquals(oneNode.root.getKey().intValue(), 7);
        assertNull(oneNode.root.successor());
        assertEquals(oneNode.size(), 1);
        assertThrows("Checks to see if exception is thrown",
            NullPointerException.class, () -> {
            oneNode.insert(null,39);});
        Integer hello = tree.insert(10,59);
        assertEquals((Integer) 10, hello);
        oneNode.insert(10,11);
        oneNode.insert(9,39);
        oneNode.insert(8,39);
        oneNode.insert(6,39);
        oneNode.insert(70,39);
        oneNode.insert(5,39);
        oneNode.insert(5,39);
        oneNode.insert(3,39);
        oneNode.insert(1,39);
        assertEquals(oneNode.root.getLeft().getLeft().getLeft(). 
        getLeft().getKey().intValue(), 1);
    }

    /**
     * Tests the search method thoroughly
     */
    @Test
    public void testSearch() {
        assertEquals(30, tree.search(3).intValue());
        assertNull(tree.search(10));
        assertNull(tree.search(null));
        assertEquals(2, tree.search(1).intValue());
        this.oneNode = new MyBST<>();
        MyBST.MyBSTNode<Integer, Integer> three =
        new MyBST.MyBSTNode<>(3, 30, null);
        oneNode.root = three;
        assertEquals(oneNode.search(3), (Integer) 30);



    }

    /**
     * Tests the remove method thoroughly.
     */
    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root2 = tree.root;
        assertEquals(30, tree.remove(3).intValue());
        assertNull(root2.getLeft().getRight());
        assertEquals(1, tree.remove(6).intValue());
        assertEquals(5, root2.getRight().getKey().intValue());
        assertEquals("size of tree", 4, tree.size);
        this.oneNode = new MyBST<>();
        oneNode.insert(10,12);
        oneNode.insert(9,39);
        oneNode.insert(11,39);
        oneNode.insert(16,39);
        oneNode.insert(8,39);
        oneNode.insert(2,39);
        oneNode.insert(15,39);
        oneNode.insert(1,39);
        oneNode.insert(5,39);
        oneNode.insert(14,39);
        oneNode.insert(17,39);
        //oneNode.remove()
        Integer remove = oneNode.remove(10);
        assertEquals((Integer) 12, remove);
        assertEquals(oneNode.root.getKey(), (Integer) 11);
        assertEquals(oneNode.root.getLeft().getKey(), (Integer) 9);
        assertEquals(oneNode.root.getLeft().getKey(), (Integer) 9);
        assertEquals(oneNode.root.getRight().getKey(), (Integer) 16);

    }

    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        assertEquals(expectedRes, tree.inorder());
        this.oneNode = new MyBST<>();
        assertEquals(new ArrayList<>(), oneNode.inorder());
        oneNode.insert(10,12);
        oneNode.insert(9,39);
        oneNode.insert(11,39);
        oneNode.insert(16,39);
        oneNode.insert(8,39);
        oneNode.insert(2,39);
        oneNode.insert(15,39);
        oneNode.insert(1,39);
        oneNode.insert(5,39);
        oneNode.insert(14,39);
        oneNode.insert(17,39);
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes2 =
        new ArrayList<>();
        expectedRes2.add(oneNode.root.getLeft().getLeft().getLeft().getLeft());        
        expectedRes2.add(oneNode.root.getLeft().getLeft().getLeft());
        expectedRes2.add(oneNode.root.getLeft().getLeft().getLeft().getRight());
        expectedRes2.add(oneNode.root.getLeft().getLeft());
        expectedRes2.add(oneNode.root.getLeft());
        expectedRes2.add(oneNode.root);
        expectedRes2.add(oneNode.root.getRight());
        expectedRes2.add(oneNode.root.getRight().getRight().getLeft().getLeft());
        expectedRes2.add(oneNode.root.getRight().getRight().getLeft());
        expectedRes2.add(oneNode.root.getRight().getRight());
        expectedRes2.add(oneNode.root.getRight().getRight().getRight());
        assertEquals(expectedRes2, oneNode.inorder());
        assertThrows("Checks to see if exception is thrown",
        IndexOutOfBoundsException.class, () -> {
        oneNode.inorder().get(11);});

    }
}
