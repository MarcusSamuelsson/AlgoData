/*

Author: Marcus Samuelsson
Date, last updated: 2020-10-07
Problem approached: Lab 4 - Graphs, problem 1,2,3

*/

package lab4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // Node first in the queue
    private Node<Item> last;     // Node last in the queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;  //Variable that holds the data element in the node
        private Node<Item> next;    //The node next in queue
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;   //Setting the first node to null
        last = null;    //Setting the last node to null
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;   //Check and return if there is no current elements in the list
    }

    public int size() {
        return n;   //Returns the size of the queue
    }

    //Peek on the first element in the queue
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    //Enqueues a node in the front of the queue
    public void enqueue(Item item) {
        Node<Item> oldlast = last;  //Saves the current node in the last position of the queue
        last = new Node<Item>();    //Creates a new node in the last position of the queue
        last.item = item;   //Sets the item to the char sent in for the node
        last.next = null;

        if (isEmpty())
            first = last;
        else
            oldlast.next = last;

        n++;
    }

    //Dequeues the first node in the queue
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");

        Item item = first.item;
        first = first.next;
        n--;

        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
