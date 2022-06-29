/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-20
Problem approached: Lab 2 - Sorting, problem 6

*/

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class MergeCutoff6 {  //Code imported and based on code from princeton (https://algs4.cs.princeton.edu/22mergesort/Merge.java.html)

    //Prints out sent in array
    static private void PrintArray(int[] array) {
        StdOut.print("[");
        for (int i = 0; i < array.length; i++) {
            StdOut.print(array[i]);

            if (i < array.length - 1)   //Sets out a ',' behind every number except the last
                StdOut.print(", ");
        }
        StdOut.println("]");
    }

    //Generates and fills an array with random number between 0-99999
    static int[] generate(int size) {
        Random rand = new Random();

        int[] array = new int[size];

        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(100000);

        return array;
    }

    private static void merge(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)    //if is grater than the middle number
                dst[k] = src[j++];
            else if (j > hi)    //if is grater than the high number
                dst[k] = src[i++];
            else if (src[j] < src[i]) //else if src[j] < src[i] then set dst[k] to src[j] and increase j
                dst[k] = src[j++];   // to ensure stability
            else    //else if src[j] > src[i] then set dst[k] to src[i] and increase i
                dst[k] = src[i++];
        }
    }

    private static void sort(int[] src, int[] dst, int lo, int hi, int cutoff) {
        // if (hi <= lo) return;
        if (hi <= lo + cutoff) {
            insertionSort(dst, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid, cutoff);
        sort(dst, src, mid + 1, hi, cutoff);

        // using System.arraycopy() is a bit faster than the above loop
        if (src[mid + 1] > src[mid]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        merge(src, dst, lo, mid, hi);
    }

    public static void sort(int[] a, int cutoff) {
        int[] aux = a.clone();
        sort(aux, a, 0, a.length - 1, cutoff);
    }

    static private void insertionSort(int[] array, int lo, int hi) {
        int holding;    //Holds the element currently being moved

        //Goes from the second int in the array
        for (int i = lo; i <= hi; i++) {
            int x = i - 1;  //The previous index in the array
            holding = array[i];

            //While the number behind the one currently in hold is lower
            while (x >= lo && array[x] > holding) {
                array[x + 1] = array[x];    //Move bigger number to the right
                x--;    //Decrease x to check next number to the left
            }
            array[x + 1] = holding; //Put holding infront of the smaller number
        }
    }

    public static void main(String[] args) {
        int[] array = generate(1000000);
        long startTime, average = 0;

        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j < 10; j++) {
                startTime = System.nanoTime();
                sort(array, i);
                average += ((System.nanoTime() - startTime) / 1000000);
            }

            average = average / 10;

            StdOut.println("Total execution time for cutoff " + i + ": "
                    + average + " ms");

        }
    }
}
