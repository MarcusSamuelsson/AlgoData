/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-09
Problem approached: Lab 1 - The Fundamentals, problem 5

Inputs: Fixed number of characters from main function
Outputs: Prints after each use of dequeue and enqueue

*/

import edu.princeton.cs.algs4.StdOut;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IndexQueue<Item> implements Iterable<Item> {   //Code imported and based on code from princeton (https://algs4.cs.princeton.edu/13stacks/Queue.java.html)
    static private int n = 0;        // number of elements on list
    private Node first;     // Node first in the queue
    private Node last;     // Node last in the queue

    public IndexQueue() {
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
            last.prev = last;
            last.next = last;
            first = last;
        } else {    //If there are elements in the queue
            last.prev = oldLast;
            first.prev = last;
            oldLast.next = last;
            last.next = first;
        }

        n++;
    }

    public void dequeue(int index) {
        if (n == 0 || index < 1 || index > n) {   //If there are no elements in queue print message and return
            StdOut.println("No elements to dequeue at index");
            return;
        } else if (n == 1) {
            first = null;
            last = null;
        } else {
            if (index == n) {    //If the node is first
                first = first.next;
                first.prev = last;
                last.next = first;
            } else if (index == 1) {    //If the node is last
                last = last.prev;
                last.next = first;
                first.prev = last;
            } else if (index < n && index > 1) { //Check if the element being dequeued isn't the first or last node
                Node currentNode = last.prev;

                //Loops through until the node being dequeued has been found
                for (int i = 2; i < index; i++) {
                    currentNode = currentNode.prev;
                }

                currentNode.prev.next = currentNode.next;   //Changes next on the node infront of the one being dequeued
                currentNode.next.prev = currentNode.prev;   //Changes prev on the node behind of the one being dequeued
            }
        }

        n--;    //Decrease number of elements
    }

    public ListIterator<Item> iterator() {
        return new IndexQueueIterator();
    }

    // assumes no calls to IndexQueue.add() during iteration
    private class IndexQueueIterator implements ListIterator<Item> {
        private Node current = first;  // the node that is returned by next()

        private int index = 0;

        //Checks if there is a node in .next
        public boolean hasNext() {
            return index < n;
        }

        //Checks if there is a node in .prev
        public boolean hasPrevious() {
            return index > 0;
        }

        public int previousIndex() { //Not used but necessary for the iterator
            return 0;
        }

        public int nextIndex() {    //Not used but necessary for the iterator
            return 0;
        }

        //Changes current variable to the next node
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        //Changes current variable to the previous node
        public Item previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
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
            if (i < n - 1)
                s.append(',');  //Added to make the output look better
            i++;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        IndexQueue<Character> doublyLinkedList = new IndexQueue<>();

        StdOut.println("\n-----Testing empty-----");
        StdOut.println("\nExpected number of nodes: 0 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueueing abc and dequeueing index 2----");
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
        StdOut.println("Dequeueing at index 2");
        doublyLinkedList.dequeue(2);
        StdOut.println("Expected elements: ac \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 2 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueue d and e then dequeue index 3-----");
        c = 'd';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: acd \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");

        c = 'e';
        doublyLinkedList.enqueue(c);
        StdOut.println("Expected elements: acde \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 4 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue(3);
        StdOut.println("Dequeueing at index 3");
        StdOut.println("Expected elements: ade \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");

        StdOut.println("Dequeueing at index 1");
        doublyLinkedList.dequeue(1);
        StdOut.println("Expected elements: ad \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 2 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n\n-----Testing to dequeue everything-----");
        StdOut.println("Dequeueing at index 2");
        doublyLinkedList.dequeue(2);
        StdOut.println("Expected elements: d \nActual elements: " + "[" + doublyLinkedList.toString() + "]\n");
        StdOut.println("Expected number of nodes: 1 \nActual number of nodes: " + n + "\n");
        StdOut.println("Dequeueing at index 1");
        doublyLinkedList.dequeue(1);
        StdOut.println("Expected number of nodes: 0 \nActual number of nodes: " + n + "\n");
        doublyLinkedList.dequeue(1);
    }
}
