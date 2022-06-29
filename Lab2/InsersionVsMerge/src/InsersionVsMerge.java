import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class InsersionVsMerge {
    static private void PrintArray(int[] array, int size) {
        StdOut.print("[");
        for (int i = 0; i < size; i++) {
            StdOut.print(array[i]);

            if (i < size - 1)
                StdOut.print(", ");
        }
        StdOut.println("]");
    }

    static int[] generate(int size) {
        Random rand = new Random();

        int[] array = new int[size];

        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(100000);

        return array;
    }

    static void InsersionSort(int[] array) {
        int holding;

        for (int i = 1; i < array.length; i++) {
            int x = i - 1;
            holding = array[i];

            while (x >= 0 && array[x] > holding) {
                array[x + 1] = array[x];
                x--;
            }
            array[x + 1] = holding;
        }
    }

    static public void MergeSort(int[] array, int left, int right) {
        if (left < right) {
            int m = (left + right) / 2;

            MergeSort(array, left, m);
            MergeSort(array, (m + 1), right);

            Merge(array, left, m, right);
        }
    }

    static private void Merge(int[] array, int left, int middle, int right) {
        int n1 = (middle - left + 1), n2 = right - middle;

        int[] arrayL = new int[n1];
        int[] arrayR = new int[n2];

        //StdOut.print("left: " + left + "\nright: " + right + "\n");

        for (int i = 0; i < n1; ++i)
            arrayL[i] = array[left + i];

        for (int j = 0; j < n2; ++j)
            arrayR[j] = array[middle + 1 + j];

        int l = 0;
        int r = 0;
        int k = left;

        while (l < n1 && r < n2) {
            if (arrayL[l] <= arrayR[r]) {
                array[k] = arrayL[l];
                l++;
            } else {
                array[k] = arrayR[r];
                r++;
            }
            k++;
        }

        while (l < n1) {
            array[k] = arrayL[l];
            l++;
            k++;
        }

        while (r < n2) {
            array[k] = arrayR[r];
            r++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] array;
        int[] array2;
        int powOf, medianIn = 0, medianMe = 0;

        long startTime, elapsedTime;

        for (int i = 0; i <= 6; i++) {
            powOf = (int) Math.pow(10, i);

            for (int j = 0; j < 3; j++) {
                array = generate(powOf);
                array2 = array.clone();

                startTime = System.nanoTime();
                MergeSort(array, 0, array.length - 1);
                elapsedTime = System.nanoTime() - startTime;
                medianMe += elapsedTime / 1000000;

                startTime = System.nanoTime();
                InsersionSort(array2);
                elapsedTime = System.nanoTime() - startTime;
                medianIn += elapsedTime / 1000000;

            }

            medianMe = medianMe / 3;
            medianIn = medianIn / 3;

            StdOut.println("Total execution time of mergesort for 10^" + i + " elements: "
                    + medianMe);

            StdOut.println("Total execution time of insertionsort for 10^" + i + " elements: "
                    + medianIn);

        }


    }
}
