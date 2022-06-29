/*

Author: Marcus Samuelsson
Date, last updated: 2020-10-07
Problem approached: Lab 4 - Graphs, problem 3

*/

package lab4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class DirectedGrapth3 {
    public static Node[] graph;
    public static String path = "";

    public static class Node {
        public String id;
        Node[] adjacent;
        boolean marked = false;
        Node currParent = null;

        private Node(String id) {
            this.id = id;
        }
    }

    //Adds nodes to the array from a textfile
    private void addNodes(String filename) {
        BinarySearchST3 st = new BinarySearchST3<String, Integer>();

        //Get file at filename with In
        In in = new In(filename);

        //Return if in is empty
        if (in.isEmpty())
            return;

        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(" ");

            //Loop through one line at a time (1-way from the left node at a time)
            //Set adjacent depending on which side the node is on
            //Only if the node is on the left side of the line will it
            //get adjacent with the one on the right
            for (int i = 0; i < a.length; i++) {
                if (i % 2 == 0) {
                    if (!st.contains(a[i]))
                        st.put(a[i], 1, a[i + 1]);
                    else
                        st.put(a[i], (st.get(a[i]) + 1), a[i + 1]);
                } else {
                    if (!st.contains(a[i]))
                        st.put(a[i], 0, "");
                    else
                        st.put(a[i], (st.get(a[i])), "");
                }
            }
        }

        //Set graph array size based on number of nodes found in st
        graph = new Node[st.size()];

        //Set value and number of adjacent
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Node((String) st.keys[i]);
            graph[i].adjacent = new Node[st.get(graph[i].id)];
        }

        //Set all adjacent to each node
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].adjacent.length; j++) {
                graph[i].adjacent[j] = getNode(st.pairs[i].substring(j * 2, j * 2 + 2));
            }
        }

    }

    //Get a node inte graph array based on id
    private static Node getNode(String id) {
        for (Node node : graph) {
            if (id.equals(node.id))
                return node;
        }

        return null;
    }

    //Reset marked value on all nodes in the graph array
    private static void resetMarked() {
        for (Node node : graph) {
            node.marked = false;
        }
    }

    //Get start and goal node supposed to be found with DFS
    public static boolean hasPathDFS(String start, String goal) {
        Node s = getNode(start);
        Node g = getNode(goal);
        return hasPathDFS(s, g);
    }

    //Go through adjacent nodes starting from start node until
    //goal node ha been found to be adjacent
    //Then return true while typing out path backwards
    private static boolean hasPathDFS(Node start, Node goal) {
        if (start.marked)
            return false;

        start.marked = true;

        if (start == goal)
            return true;

        for (int i = 0; i < start.adjacent.length; i++) {
            if (hasPathDFS(start.adjacent[i], goal)) {
                path = start.adjacent[i].id + " " + path;
                return true;
            }
        }

        return false;
    }

    //Get start and goal node supposed to be found with BFS
    public static boolean hasPathBFS(String start, String goal) {
        Node s = getNode(start);
        Node g = getNode(goal);
        return hasPathBFS(s, g);
    }

    //Go through and check if any current child is goal
    //if not then add all children to a queue and go through
    //each child as they get added.
    public static boolean hasPathBFS(Node start, Node goal) {
        Queue<Node> next = new Queue<>();
        next.enqueue(start);

        while (!next.isEmpty()) {
            Node n = next.peek();
            next.dequeue();

            if (n == goal) {
                Node pre = n;

                //Go back and check what path was taken
                //to get to the goal node and print to path
                while (pre.currParent != null) {
                    path = pre.id + " " + path;
                    pre = pre.currParent;
                }

                return true;
            }

            if (n.marked)
                continue;

            n.marked = true;

            for (int i = 0; i < n.adjacent.length; i++) {
                next.enqueue(n.adjacent[i]);

                //Set current as parent to adjacent so that it is possible
                //to backtrace the path taken to get to goal
                if (!n.adjacent[i].marked && n.adjacent[i].currParent == null)
                    n.adjacent[i].currParent = n;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DirectedGrapth3 ug = new DirectedGrapth3();
        StdOut.println("Find the a path from X to Y");
        StdOut.print("X: ");
        String start = StdIn.readString();
        StdOut.print("Y: ");
        String end = StdIn.readString();

        ug.addNodes("E:\\KTH\\Algodata\\Labb4\\contiguous-usa.dat.txt");

        path = "";
        if (hasPathDFS(start, end))
            StdOut.println("With DFS: " + start + " " + path);
        else
            StdOut.println("DFS could not find a path");

        resetMarked();

        path = "";
        if (hasPathBFS(start, end))
            StdOut.println("With BFS: " + start + " " + path);
        else
            StdOut.println("BFS could not find a path");
    }
}
