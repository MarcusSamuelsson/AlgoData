/*

Author: Marcus Samuelsson
Date, last updated: 2020-10-07
Problem approached: Lab 4 - Graphs, problem 3

*/

package lab4;

import java.util.NoSuchElementException;

public class BinarySearchST3<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    public Key[] keys;
    private int[] vals;
    public String[] pairs;
    private int n = 0;

    //Initializes and empty symbol table
    public BinarySearchST3() {
        this(INIT_CAPACITY);
    }

    //Initializes and empty symbol table with a specified capacity
    public BinarySearchST3(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = new int[capacity];
        pairs = new String[capacity];
    }

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= n;
        Key[] tempk = (Key[]) new Comparable[capacity];
        int[] tempv = new int[capacity];
        String[] tempp = new String[capacity];

        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
            tempp[i] = pairs[i];
        }

        vals = tempv;
        keys = tempk;
        pairs = tempp;
    }

    //Returns the number of key-values in the table
    public int size() {
        return n;
    }

    //Returns if the table is empty or not
    public boolean isEmpty() {
        return size() == 0;
    }

    //Checks if the table contains the sent in key
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != 0;
    }

    //Returns the value associated with the sent in key
    public int get(Key key) {
        if (key == null)    //Makes sure the sent in key isn't null
            throw new IllegalArgumentException("argument to get() is null");

        if (isEmpty())  //Makes sure the sent in key isn't empty
            return 0;

        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0)
            return vals[i];

        return 0;
    }

    //Returns the number of keys less than the specified key sent in
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }

        return lo;
    }


    //Inserts the specified key-value pair into the symbol table
    public void put(Key key, int val, String par) {
        if (key == null)    //If the key is null gives error
            throw new IllegalArgumentException("first argument to put() is null");

        //Because nodes without adjacent nodes(value) can be entered this line has to be removed
        /*if (val == 0) {  //If a key without a value is entered, the key is deleted
            delete(key);
            return;
        }*/

        int i = rank(key);

        // key is already in table
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            pairs[i] += par;
            return;
        }

        // insert new key-value pair
        if (n == keys.length)
            resize(2 * keys.length);

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
            pairs[j] = pairs[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        pairs[i] = par;
        n++;
    }

    //Removes the sent in key and associated value from the table
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = 0;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);
    }

    //Returns the smallest key in the table
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    //Returns the largest key in the table
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n - 1];
    }

    //Returns all keys as iterable so they can be printed out with a foreach
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    //Returns keys the table in the range between lo to hi
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }
}
