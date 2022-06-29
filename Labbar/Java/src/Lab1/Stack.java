package Lab1;

/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-01
Problem approached: Lab 1 - The Fundamentals, problem 2, ADT

Stack class

*/

import java.util.NoSuchElementException;

public class Stack {

    private class Node {
        char c; //Variable that holds the data element in the node
        Node next;  //The node next in the stack
    }

    private Node first; // Node first in the stack

    public Stack() {    //Iterating stack
        first = null;   //Setting the first node to null
    }

    public boolean isEmpty() {
        return first == null;   //Check and return if there is no current elements in the list
    }

    //Pushes new node into the stack
    public void push(char c) {
        Node oldfirst = first;  //Saves the current node in first
        first = new Node(); //Creates a new node in first
        first.c = c;    //Sets the char to the char sent in the parameter
        first.next = oldfirst;  //Sets oldfirst as next of the new
    }

    //Removes node from first in the stack
    public char pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        char c = first.c;        // save item to return
        first = first.next;            // delete first node
        return c;                   // return the saved item
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        while (!isEmpty()) {        //If the stack is not empty, we pop() the current element
            s.append(pop());
            if (!isEmpty())
                s.append(",");
        }
        return s.toString();
    }
}
