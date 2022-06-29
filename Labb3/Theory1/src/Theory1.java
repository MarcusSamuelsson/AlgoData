/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-28
Problem approached: Lab 3 - Searching, theory problem 1

*/

import edu.princeton.cs.algs4.StdOut;

class Theory1 {
    public static Node root;

    //Adds a new node to the table
    //Sets as root if root == null
    static void addNode(char key) {
        Node newNode = new Node(key);

        if (root == null)
            root = newNode;
        else {
            Node currNode = root;

            Node parent;

            while (true) {
                parent = currNode;

                if (Character.compare(newNode.key, currNode.key) < 0) {
                    currNode = currNode.leftChild;

                    if (currNode == null) {
                        parent.leftChild = newNode;
                        StdOut.println(newNode.key + " added to left side of " + parent.key);
                        return;
                    }

                } else {
                    currNode = currNode.rightChild;

                    if (currNode == null) {
                        parent.rightChild = newNode;
                        StdOut.println(newNode.key + " added to right side of " + parent.key);
                        return;
                    }
                }
            }
        }

    }

    //Prints out the table in preOrder
    static void preOrderPrint(Node currNode) {
        if (currNode != null) {
            StdOut.print(currNode.key);

            preOrderPrint(currNode.leftChild);

            preOrderPrint(currNode.rightChild);
        }
    }

    //Prints out the table in inOrder
    static void inOrderPrint(Node currNode) {
        if (currNode != null) {
            inOrderPrint(currNode.leftChild);

            StdOut.print(currNode.key);

            inOrderPrint(currNode.rightChild);
        }
    }

    //Prints out the table in postOrder
    static void postOrderPrint(Node currNode) {
        if (currNode != null) {
            postOrderPrint(currNode.leftChild);

            postOrderPrint(currNode.rightChild);

            StdOut.print(currNode.key);
        }
    }

    public static void main(String[] args) {
        char[] array = {'W', 'O', 'E', 'C', 'A', 'L', 'H'};

        for (int i = 0; i < array.length; i++)
            addNode(array[i]);

        preOrderPrint(root);
        StdOut.println("");

        inOrderPrint(root);
        StdOut.println("");

        postOrderPrint(root);
    }
}

class Node {
    char key;

    Node leftChild;
    Node rightChild;

    Node(char key) {
        this.key = key;
    }
}
