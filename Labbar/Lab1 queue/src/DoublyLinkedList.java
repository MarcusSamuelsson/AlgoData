/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-09
Problem approached: Lab 1 - The Fundamentals, problem 3

Inputs: Fixed number of characters from main function
Outputs: Prints after each use of dequeue and enqueue

*/

import edu.princeton.cs.algs4.StdOut;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> { //Code imported and based on code from princeton (https://algs4.cs.princeton.edu/13stacks/Queue.java.html)
    static private int n = 0;        // number of elements on list
    private Node first;     // Node first in the queue
    private Node last;     // Node last in the queue

    public DoublyLinkedList() {
        first = null;   //Setting the first node to null
        last = null;    //Setting the last node to null
    }

    // linked list node helper data type
    private class Node {
        private Item item;  //Variable that holds the data element in the node
        private Node next;  //The node next in queue
        private Node prev;  //The node in front of/previously in queue
    }

    public boolean isEmpty() {
        return n == 0;  //Check and return if there is no current elements in the list
    }

    public int size() {
        return n;   //Returns the size of the queue
    }

    // add the item to the list
    public void enqueue(Item item) {
        Node oldLast = last;    //Saves the current node in the last position of the queue
        last = new Node();  //Creates a new node in the last position of the queue
        last.item = item;   //Sets the item to the char sent in for the node

        if (isEmpty()) {    //If there is no current elements in the queue
            last.prev = last;   //Sets the prev, next last and first to itself
            last.next = last;
            first = last;
        } else {    //If there are elements in the queue
            last.prev = oldLast;    //Sets prev on the new node to oldLast
            first.prev = last;  //Sets first.prev to the new node because of the double linked list
            oldLast.next = last;    //Sets oldLast.next to the new node in the last position
            last.next = first;  //Sets last.prev to the first node because of the double linked list
        }


        n++;    //Increases the number of elements
    }

    public void dequeue() {
        if (n <= 0) {   //If there are no elements in queue print message and return
            StdOut.println("No elements to dequeue");
            return;
        }
        n--;    //Decrease number of elements

        first.next.prev = last; //Sets prev on the node next to first
        first = first.next; //Sets first to the node next in queue of the current first
        last.next = first; //Sets last.nex to the new node in first position
    }

    public ListIterator<Item> iterator() {
        return new DoublyLinkedListIterator();
    }

    // assumes no calls to DoublyLinkedList.add() during iteration
    private class DoublyLinkedListIterator implements ListIterator<Item> {
        private Node current = first;  // the node that is returned by next()
        private int index = 0;

        //Checks if there is a node in .next
        public boolean hasNext() {
            return index < n;
        }

        //Checks if there is a node in .prev
        public boolean hasPrevious() {
            return false;
        }

        public int previousIndex() {    //Not used but necessary for the iterator
            return 0;
        }

        public int nextIndex() {    //Not used but necessary for the iterator
            return 0;
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

        //Changes current variable to the previous node
        public Item previous() {
            current = current.prev;
            return current.item;
        }

        public void set(Item item) {    //Not used but necessary for the iterator

        }

        public void remove() {  //Not used but necessary for the iterator

        }

        public void add(Item item) {    //Not used but necessary for the iterator

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
        DoublyLinkedList<Character> doublyLinkedList = new DoublyLinkedList<>();

        StdOut.println("\n-----Testing empty-----");
        StdOut.println("\nExpected number of nodes: 0 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueue/dequeue with abc----");
        char c = 'a';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: a \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        c = 'b';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: ab \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        c = 'c';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: abc \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue();
        StdOut.println("Expected elements: bc \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 2 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueue d and e then dequeue b-----");
        c = 'd';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: bcd \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");

        c = 'e';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: bcde \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 4 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue();
        StdOut.println("Expected elements: cde \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing to see if the queue is double linked circular-----");
        ListIterator<Character> listIterator = doublyLinkedList.iterator();

        StdOut.println("Going through with previous() and last is repeat\n" +
                "Expected elements: edce");
        StdOut.print("Actual elements: ");
        for (int i = 0; i < 4; i++)
            StdOut.print(listIterator.previous());

        listIterator = doublyLinkedList.iterator();

        StdOut.println("\n\nGoing through with next() and last is repeat\n" +
                "Expected elements: cdec");
        StdOut.print("Actual elements: ");
        for (int i = 0; i < 4; i++)
            StdOut.print(listIterator.next());

        StdOut.println("\n\n-----Testing to dequeue everything-----");
        doublyLinkedList.dequeue();
        StdOut.println("Expected elements: de \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 2 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue();
        StdOut.println("Expected elements: e \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 1 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue();
        StdOut.println("Expected number of nodes: 0 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue();

    }
}
