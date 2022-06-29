/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-28
Problem approached: Lab 3 - Searching, problem 2, 4

*/

package lab3;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    //Initializes and empty symbol table
    public BST() {
    }

    //Initializes and empty symbol table with a specified capacity
    public boolean isEmpty() {
        return size() == 0;
    }

    //Returns the number of key-value pairs in the table
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    //Checks if the table contains the specified in key
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");

        return get(key) != null;
    }

    //Returns the value associated with the sent in key
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");

        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    //Inserts the specified key-value pair into the symbol table
    public void put(Key key, Value val) {
        if (key == null)    //If the key is null gives error
            throw new IllegalArgumentException("calls put() with a null key");

        if (val == null) {  //If a key without a value is entered, the key is deleted
            delete(key);
            return;
        }

        root = put(root, key, val);
        assert check();
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;

        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    //Removes the sent in key and associated value from the table
    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls delete() with a null key");

        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }


    //Return the smallest key in the table
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");

        return min(root).key;
    }

    //Return the node with the smallest key in the table
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    //Return the largest key in the table
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("calls max() with empty symbol table");

        return max(root).key;
    }

    //Return the node with the largest key in the table
    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    //Returns the smallest key in the table
    public Key select(int rank) {
        if (rank < 0 || rank >= size())
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);

        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null)
            return null;

        int leftSize = size(x.left);

        if (leftSize > rank)
            return select(x.left, rank);
        else if (leftSize < rank)
            return select(x.right, rank - leftSize - 1);
        else
            return x.key;
    }

    //Returns how many keys that are smaller than the given key
    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to rank() is null");

        return rank(key, root);
    }

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null)
            return 0;

        int cmp = key.compareTo(x.key);

        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    //Returns all keys as iterable so they can be printed out with a foreach
    public Iterable<Key> keys() {
        if (isEmpty())
            return new Queue<Key>();

        return keys(min(), max());
    }

    //Returns keys the table in the range between lo to hi
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null)
            throw new IllegalArgumentException("first argument to keys() is null");

        if (hi == null)
            throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left, queue, lo, hi);

        if (cmplo <= 0 && cmphi >= 0)
            queue.enqueue(x.key);

        if (cmphi > 0)
            keys(x.right, queue, lo, hi);
    }

    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }
}
