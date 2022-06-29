/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-28
Problem approached: Lab 3 - Searching, problem 3

*/

package lab3;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    public SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


    //Initializes and empty symbol table
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    //Initializes and empty symbol table with a specified chains m
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    // hash function for keys - returns value between 0 and m-1 (assumes m is a power of 2)
    // (from Java 7 implementation, protects against poor quality hashCode() implementations)
    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);
    }

    //Inserts the specified key-value pair into the symbol table
    public void put(Key key, Value val) {
        if (key == null)    //If the key is null gives error
            throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {  //If a key without a value is entered, the key is deleted
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (n >= 10 * m) resize(2 * m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    }

    //Removes the sent in key and associated value from the table
    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
    }


    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> hashTable = new SeparateChainingHashST<String, Integer>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            hashTable.put(key, i);
        }

        int min = hashTable.st[0].size(), max = hashTable.st[0].size(), average = 0;

        for (int i = 0; i < hashTable.st.length; i++) {
            average += hashTable.st[i].size();

            if (hashTable.st[i].size() < min)
                min = hashTable.st[i].size();

            if (hashTable.st[i].size() > max)
                max = hashTable.st[i].size();


            StdOut.println("Hashtable array at index " + i + " has size: " + hashTable.st[i].size());
        }

        average = average / hashTable.st.length;    //Gets average length

        StdOut.println("Smallest array size: " + min);
        StdOut.println("Biggest array size: " + max);
        StdOut.println("Average array size: " + average);

    }
}
