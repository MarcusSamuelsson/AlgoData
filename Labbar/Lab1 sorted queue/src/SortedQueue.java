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

public class SortedQueue implements Iterable<Integer> {     //Code imported and based on code from princeton (https://algs4.cs.princeton.edu/13stacks/Queue.java.html)
    static private int n = 0;        // number of elements on list
    private Node first;     // Node first in the queue
    private Node last;     // Node last in the queue

    public SortedQueue() {
        first = null;   //Setting the first node to null
        last = null;    //Setting the last node to null
    }

    // linked list node helper data type
    private static class Node {
        private Integer item;   //Variable that holds the char in the node
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
    public void enqueue(Integer item) {
        Node newInsert = new Node();    //Creates a new node
        newInsert.item = item;  //Sets the item to the item in the parameter

        if (isEmpty()) {    //If there is no current elements in the queue
            last = newInsert;
            last.prev = last;
            last.next = last;
            first = last;
        } else {    //If there are elements in the queue
            Node currentNode = last;    //New node equal to the last node

            //If the newInsert.item is less than the first.item
            //Then the newInsert is put infront of the current element in first
            if (newInsert.item.compareTo(first.item) < 0) {
                first.prev = newInsert;
                last.next = newInsert;
                newInsert.next = first;
                newInsert.prev = last;
                first = newInsert;
            } else if (newInsert.item.compareTo(last.item) > 0) {   //If the newInsert.item is greater than the last.item
                //Then the newInsert is put behind of the current element in last
                first.prev = newInsert;
                last.next = newInsert;
                newInsert.next = first;
                newInsert.prev = last;
                last = newInsert;
            } else {    //If the number is not meant to be in first or last
                currentNode = currentNode.next;

                //Loop through the nodes from the on infront of the last
                //Until the newInsert.item is bigger than the currentNode.item
                //Then insert the newInsert behind the currentNode (currentNode.next)
                //And exit the loop
                for (int i = 2; i <= n; i++) {
                    if (newInsert.item.compareTo(currentNode.item) < 0) {
                        currentNode = currentNode.prev;
                    } else {
                        newInsert.next = currentNode.next;
                        newInsert.prev = currentNode;
                        currentNode.next.prev = newInsert;
                        currentNode.next = newInsert;
                        break;
                    }
                }
            }
        }

        n++;    //Increases the number of elements
    }

    public void dequeue() {
        if (n <= 0) {   //Check if there are no elements in the queue
            StdOut.println("No elements to dequeue");
            return;
        }
        n--;    //Decrease number of elements

        first.next.prev = last; //Sets prev on the node next to first
        first = first.next; //Sets first to the node next in queue of the current first
        last.next = first; //Sets last.nex to the new node in first position
    }

    public ListIterator<Integer> iterator() {
        return new SortedQueueIterator();
    }

    // assumes no calls to SortedQueue.add() during iteration
    private class SortedQueueIterator implements ListIterator<Integer> {
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

        public int previousIndex() {    //Not used but necessary for the iterator
            return 0;
        }

        public int nextIndex() {    //Not used but necessary for the iterator
            return 0;
        }

        //Changes current variable to the next node
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer item = current.item;
            current = current.next;
            index++;
            return item;
        }

        //Changes current variable to the previous node
        public Integer previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            return current.item;
        }

        public void set(Integer item) { //Not used but necessary for the iterator

        }

        public void remove() {  //Not used but necessary for the iterator

        }

        public void add(Integer item) { //Not used but necessary for the iterator

        }

    }

    public String toString() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (Integer item : this) {
            s.append(item);
            if (i < n - 1)
                s.append(',');  //Added to make the output look better
            i++;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        SortedQueue doublyLinkedQueue = new SortedQueue();

        StdOut.println("\n-----Testing empty-----");
        StdOut.println("\nExpected number of nodes: 0 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueueing 312 and dequeueing index 2----");
        doublyLinkedQueue.enqueue(3);
        StdOut.println("Expected elements: 3 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");
        doublyLinkedQueue.enqueue(1);
        StdOut.println("Expected elements: 13 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");
        doublyLinkedQueue.enqueue(2);
        StdOut.println("Expected elements: 123 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");
        doublyLinkedQueue.dequeue();
        StdOut.println("Expected elements: 23 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");
        StdOut.println("Expected number of nodes: 2 \nActual number of nodes: " + n + "\n");

        StdOut.println("\n-----Testing enqueue d and e then dequeue index 3-----");
        doublyLinkedQueue.enqueue(1);
        StdOut.println("Expected elements: 123 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");

        doublyLinkedQueue.enqueue(5);
        StdOut.println("Expected elements: 1235 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");
        StdOut.println("Expected number of nodes: 4 \nActual number of nodes: " + n + "\n");
        doublyLinkedQueue.dequeue();
        StdOut.println("Expected elements: 235 \nActual elements: " + "[" + doublyLinkedQueue.toString() + "]\n");
        StdOut.println("Expected number of nodes: 3 \nActual number of nodes: " + n + "\n");

    }
}
