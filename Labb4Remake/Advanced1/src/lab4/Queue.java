package lab4;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // Node first in the queue
    private Node<Item> last;     // Node last in the queue
    private int n;               // number of elements on queue

    // linked list node helper data type
    private static class Node<Item> {
        private Item item;   //Variable that holds the char in the node
        private Integer cost;
        private Node<Item> next;  //The node next in queue
        private Node<Item> prev;  //The node in front of/previously in queue
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

    // add the item to the list
    public void enqueueSort(Item item, Integer cost) {
        Node newInsert = new Node();    //Creates a new node
        newInsert.item = item;  //Sets the item to the item in the parameter
        newInsert.cost = cost;

        if (isEmpty()) {    //If there is no current elements in the queue
            last = newInsert;
            last.prev = last;
            last.next = last;
            first = last;
        } else {    //If there are elements in the queue
            Node currentNode = last;    //New node equal to the last node

            //If the newInsert.item is less than the first.item
            //Then the newInsert is put infront of the current element in first
            if (newInsert.cost.compareTo(first.cost) < 0) {
                first.prev = newInsert;
                last.next = newInsert;
                newInsert.next = first;
                newInsert.prev = last;
                first = newInsert;
            } else if (newInsert.cost.compareTo(last.cost) > 0) {   //If the newInsert.item is greater than the last.item
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
                    if (newInsert.cost.compareTo(currentNode.cost) < 0) {
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

    public Item dequeue() {
        if (n <= 0) {   //If there are no elements in queue print message and return
            StdOut.println("No elements to dequeue");
            return null;
        }
        n--;    //Decrease number of elements

        Item item = first.item;

        first.next.prev = last; //Sets prev on the node next to first
        first = first.next; //Sets first to the node next in queue of the current first
        last.next = first; //Sets last.nex to the new node in first position

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
