package lab3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter2 {

    // Do not instantiate.
    private FrequencyCounter2() {
    }

    public static void binarySearchSTTest(String[] str) {
        int distinct = 0, words = 0;
        int minlen = 0;

        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();

        // compute frequency counts
        for (int i = 0; i < str.length; i++) {
            String key = str[i];

            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
    }

    public static void BSTTest(String[] str) {
        int distinct = 0, words = 0;
        int minlen = 0;
        int N = 0;


        BST<String, Integer> st = new BST<String, Integer>();


        // compute frequency counts
        for (int i = 0; i < str.length; i++) {
            String key = str[i];

            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
    }

    public static void main(String[] args) {
        String[] st = new String[10000];
        int distinct = 0, words = 0;
        int minlen = 0;
        int N = 100;

        // compute frequency counts
        for (int i = 0; !StdIn.isEmpty() && i < st.length; i++) {
            st[i] = StdIn.readString();
        }

        for (int i = 0; i < 100; i++) {
            long startTime, elapsedTime;
            long averageBinary = 0, averageBST = 0;
            String[] array = new String[N];

            for (int j = 0; j < N; j++) {
                array[j] = st[j];
            }


            for (int j = 0; j < 15; j++) {
                startTime = System.nanoTime();  //Starts timer
                binarySearchSTTest(array);
                elapsedTime = System.nanoTime() - startTime;
                averageBinary += elapsedTime;

                startTime = System.nanoTime();  //Starts timer
                BSTTest(array);
                elapsedTime = System.nanoTime() - startTime;
                averageBST += elapsedTime;
            }

            averageBinary = averageBinary / 15;
            averageBST = averageBST / 15;

            StdOut.println("Total execution time with " + N + " words for binarySearchST: " + (averageBinary) + " ns");
            StdOut.println("Total execution time with " + N + " words for BST: " + (averageBST) + " ns");

            N += 100;
        }

    }
}
