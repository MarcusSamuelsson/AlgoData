/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-20
Problem approached: Lab 2 - Sorting, problem 5

*/

import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public class InsersionVsMerge5 { //Code imported and based on code from princeton (https://algs4.cs.princeton.edu/22mergesort/Merge.java.html)
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

    static private void InsersionSort(int[] array) {
        int holding;    //Holds the element currently being moved

        //Goes from the second int in the array
        for (int i = 1; i < array.length; i++) {
            int x = i - 1;  //The previous index in the array
            holding = array[i];

            //While the number behind the one currently in hold is lower
            while (x >= 0 && array[x] > holding) {
                array[x + 1] = array[x];    //Move bigger number to the right
                x--;    //Decrease x to check next number to the left
            }
            array[x + 1] = holding; //Put holding infront of the smaller number
        }
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];


        // merge back to a[]
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)    //if is grater than the middle number
                a[k] = aux[j++];
            else if (j > hi)    //if is grater than the high number
                a[k] = aux[i++];
            else if (aux[j] < aux[i])   //else if aux[j] < aux[i] then set a[k] to aux[j] and increase j
                a[k] = aux[j++];
            else    //else if aux[j] > aux[i] then set a[k] to aux[i] and increase i
                a[k] = aux[i++];
        }
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;

        int mid = lo + (hi - lo) / 2;

        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(int[] a) {
        int[] aux = new int[a.length];  //Creates empty array of same size as a
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int[] array;
        int[] array2;
        int powOf;

        long startTime, elapsedTime, averageIn = 0, averageMe = 0;

        for (int i = 1; i <= 5; i++) {
            powOf = (int) Math.pow(10, i);
            averageMe = 0;
            averageIn = 0;

            //Tries 3 different arrays of same size to get average
            for (int j = 0; j < 15; j++) {
                array = generate(powOf);    //Sets array values with generate
                array2 = array.clone();     //Copies to compare insertion and merge

                startTime = System.nanoTime();  //Starts timer
                sort(array);
                elapsedTime = System.nanoTime() - startTime;    //Gets elapsed time
                averageMe += elapsedTime; //Change to milliseconds and insert into average

                startTime = System.nanoTime();  //Starts timer
                InsersionSort(array2);
                elapsedTime = System.nanoTime() - startTime;    //Gets elapsed time
                averageIn += elapsedTime; //Change to milliseconds and insert into average

            }

            averageMe = averageMe / 15;  //Get average
            averageIn = averageIn / 15;  //Get average

            StdOut.println("Total execution time of mergesort for " + powOf + " elements: "
                    + averageMe + " ns");

            StdOut.println("Total execution time of insertionsort for " + powOf + " elements: "
                    + averageIn + " ns");

        }
    }
}
