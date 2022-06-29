/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-28
Problem approached: Lab 3 - Searching, problem 4

*/

package lab3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;

class Index4 {
    public static void main(String[] args) {
        int currPlace = 0, pointer2 = 0;

        In in = new In("E:\\KTH\\Algodata\\Labb3\\input.txt");
        In in2 = new In("E:\\KTH\\Algodata\\Labb3\\input.txt");

        BST<String, List<Integer>> st = new BST<String, List<Integer>>();   //Sets value as a List


        in.readChar();
        in2.readChar();

        // compute frequency counts
        while (!in.isEmpty()) {
            String key = in.readString();   //Reads in a word from in


            if (st.contains(key)) { //Adds to symbol table
                st.get(key).add(currPlace);
                st.put(key, st.get(key));
            } else {
                st.put(key, new LinkedList<Integer>());
                st.get(key).add(currPlace);
            }

            currPlace += (key.length() + 1);    //Add word plus a space between next word

            while (!in2.isEmpty() && pointer2 < currPlace) { //Moves a pointer to check chars without moving in
                in2.readChar();
                pointer2++;
            }

            while (!in2.isEmpty() && in2.readChar() == ' ' || !in2.isEmpty() && in2.readChar() == '\n') { //Check for space or newline after the first space
                currPlace++;
            }
        }

        StdOut.print("Enter a word you want to find: ");
        StdOut.println(st.get(StdIn.readString())); //Get word from symbol table
    }
}
