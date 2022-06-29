package lab4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

class ShortestPathAdv {
    public static Node[] graph;
    public static String path = "";
    public static BinarySearchST st = new BinarySearchST<String, Integer>();
    public static Queue priority = new Queue();

    public static class Node {
        public String id;
        Node[] adjacent;
        int[] adjDist;
        boolean marked = false;
        Node currSender = null;
        int cost = Integer.MAX_VALUE;

        private Node(String id) {
            this.id = id;
        }
    }

    //Adds nodes to the array from a textfile
    private void addNodes(String filename) {
        //Get file at filename with In
        In in = new In(filename);

        //Return if in is empty
        if (in.isEmpty())
            return;

        String[] prevDist = new String[2];

        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(" ");

            if (prevDist[0] == a[1] && prevDist[1] == a[0])
                continue;

            prevDist[0] = a[0];
            prevDist[1] = a[1];

            int dist = Integer.parseInt(a[2]);

            //Loop through one line at a time (2 connected nodes at a time)
            //Set adjacent depending on which side the node is on
            for (int i = 0; i < a.length - 1; i++) {
                if (i == 0) {   //Node is on the left side in the line
                    if (!st.contains(a[i]))
                        st.put(a[i], 1, (a[i + 1] + "," + dist + ","));
                    else
                        st.put(a[i], (st.get(a[i]) + 1), (a[i + 1] + "," + dist + ","));
                } else if (i == 1) {    //Node is on the right side of the line
                    if (!st.contains(a[i]))
                        st.put(a[i], 1, (a[i - 1] + "," + dist + ","));
                    else
                        st.put(a[i], (st.get(a[i]) + 1), (a[i - 1] + "," + dist + ","));
                }
            }
        }

        //Set graph array size based on number of nodes found in st
        graph = new Node[st.size()];

        //Set value and number of adjacent
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Node((String) st.keys[i]);
            graph[i].adjacent = new Node[st.get(graph[i].id)];
            graph[i].adjDist = new int[st.get(graph[i].id)];
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].adjacent.length; j++) {
                String[] split = st.pairs[i].split(",");

                graph[i].adjacent[j] = getNode(split[j * 2]);

                graph[i].adjDist[j] = Integer.parseInt(split[j * 2 + 1]);
            }
        }
    }

    //Get a node inte graph array based on id
    private static Node getNode(String id) {
        int nodeIndex = st.getIndex(id);

        if (nodeIndex != -1)
            return graph[nodeIndex];

        return null;
    }

    //Reset marked value on all nodes in the graph array
    private static void resetMarked() {
        for (Node node : graph) {
            node.marked = false;
        }
    }

    public static void dijkstra(String start, String midWay, String end) {
        Node s = getNode(start);
        Node m = getNode(midWay);
        Node e = getNode(end);

        dijkstra(s, m);
        dijkstra(m, e);
    }

    private static void dijkstra(Node start, Node end) {
        priority.enqueueSort(start, 0);

        start.cost = 0;

        while (!priority.isEmpty()) {
            Node n = (Node) priority.dequeue();

            for (int i = 0; i < n.adjacent.length; i++) {
                if (!n.adjacent[i].marked && (n.adjDist[i] + n.cost) < n.adjacent[i].cost) {
                    n.adjacent[i].cost = (n.adjDist[i] + n.cost);
                    n.adjacent[i].currSender = n;
                }

                if (n.adjacent[i] == end) {
                    Node e = n.adjacent[i];
                    path = e.id + path;

                    while (e.currSender != null) {
                        path = e.currSender.id + path;
                        e = e.currSender;
                    }

                    return;
                }

                priority.enqueueSort(n.adjacent[i], n.adjacent[i].cost);
            }

            n.marked = true;
        }
    }

    public static void main(String[] args) {
        ShortestPathAdv ug = new ShortestPathAdv();
        ug.addNodes("E:\\KTH\\Algodata\\Labb4Remake\\test.txt");
        StdOut.println("Find the a path from X to Y");
        /*StdOut.print("X: ");
        String start = StdIn.readString();
        StdOut.print("Y: ");
        String end = StdIn.readString();*/

        dijkstra("1", "77", "98");
        StdOut.print(path);
    }
}
