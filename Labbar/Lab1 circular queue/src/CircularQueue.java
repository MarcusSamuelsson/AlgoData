/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-09
Problem approached: Lab 1 - The Fundamentals, problem 4

Inputs: Fixed number of characters from main function
Outputs: Prints after each use of dequeue and enqueue

*/

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue<Item> implements Iterable<Item> {    //Code imported and based on code from princeton (https://algs4.cs.princeton.edu/13stacks/Queue.java.html)
    static private int n = 0;        // number of elements on list
    private Node first;     // Node first in the queue
    private Node last;     // Node last in the queue

    public CircularQueue() {
        first = null;   //Setting the first node to null
        last = null;    //Setting the last node to null
    }

    // linked list node helper data type
    private class Node {
        private Item item;  //Variable that holds the data element in the node
        private Node next;  //The node next in queue
    }

    public boolean isEmpty() {
        return n == 0;  //Check and return if there is no current elements in the list
    }

    public int size() {
        return n;   //Returns the size of the queue
    }

    //Enqueues a node in the front of the queue
    public void enqueueFront(Item item) {
        Node oldFirst = first;  //Saves the current node in the first position of the queue
        first = new Node(); //Creates a new node in the first position of the queue
        first.item = item;  //Sets the item to the char sent in for the node

        if (oldFirst == null) {
            last = first;
            first.next = last;
        } else {
            first.next = oldFirst;
        }

        n++;    //Increases the number of elements
    }

    //Enqueues a node in the back of the queue
    public void enqueueBack(Item item) {
        Node oldLast = last;    //Saves the current node in the last position of the queue
        last = new Node();  //Creates a new node in the last position of the queue
        last.item = item;   //Sets the item to the char sent in for the node

        if (oldLast == null) {
            last = first;
            first.next = last;
        } else {
            oldLast.next = last;
            last.next = first;
        }

        n++;    //Increases the number of elements
    }

    //Dequeues the first node in the queue
    public void dequeueFront() {
        if (n <= 0) {   //If there are no elements in queue print message and return
            StdOut.println("No elements to dequeue");
            return;
        }
        n--;    //Decrease number of elements

        first = first.next; //Sets first to first.next
        last.next = first;
    }

    //Dequeues the last node in the queue
    public void dequeueBack() {
        if (n <= 0) {   //If there are no elements in queue print message and return
            StdOut.println("No elements to dequeue");
            return;
        }

        n--;
        Node nextTolast = first;

        //Loops until nextToLast is the node right before last
        for (int i = 0; i < n - 1; i++) {
            nextTolast = nextTolast.next;
        }

        nextTolast.next = first;    //Sets the next on the node before last to first
        last = nextTolast;  //Sets last to the node before the current last
    }


    public Iterator<Item> iterator() {
        return new CircularLinkedListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class CircularLinkedListIterator implements Iterator<Item> {
        private Node current = first;
        int index = 0;

        //Checks if there is a node in .next
        public boolean hasNext() {
            return index < n;
        }

        public void remove() {  //Not used but necessary for the iterator
            throw new UnsupportedOperationException();
        }

        //Changes current variable to the next node
        public Item next() {
            if (!hasNext())
                index = 0;
            else
                index++;

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public String toString() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (Item item : this) {
            s.append(item);
            if (i < n - 1)  //Added to make the output look better
                s.append(',');
            i++;
        }

        return s.toString();
    }

    public static void main(String[] args) {
        CircularQueue<Character> circularList = new CircularQueue<>();

        StdOut.println("\n-----Testing empty-----");
        StdOut.println("\nExpected number of nodes: 0 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueue/dequeue with abc----");
        char c = 'a';
        circularList.enqueueFront(c);
        StdOut.println("Expected elements: a \nActual elements: " + "[" + circularList.toString() + "]\n");
        c = 'b';
        circularList.enqueueBack(c);
        StdOut.println("Expected elements: ab \nActual elements: " + "[" + circularList.toString() + "]\n");
        c = 'c';
        circularList.enqueueFront(c);
        StdOut.println("Expected elements: cab \nActual elements: " + "[" + circularList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");
        circularList.dequeueFront();
        StdOut.println("Expected elements: ab \nActual elements: " + "[" + circularList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 2 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueue d and e then dequeue b-----");
        c = 'd';
        circularList.enqueueFront(c);
        StdOut.println("Expected elements: dab \nActual elements: " + "[" + circularList.toString() + "]\n");

        c = 'e';
        circularList.enqueueFront(c);
        StdOut.println("Expected elements: edab \nActual elements: " + "[" + circularList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 4 \nActual number of nodes: " + n + "\n");
        circularList.dequeueBack();
        StdOut.println("Expected elements: eda \nActual elements: " + "[" + circularList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing to see if the queue is linked circular-----");
        Iterator<Character> circIterator = circularList.iterator();

        StdOut.println("\n\nGoing through with next() and last is repeat\n" +
                "Expected elements: edae");
        StdOut.print("Actual elements: ");
        for (int i = 0; i < 4; i++)
            StdOut.print(circIterator.next());

    }
}
