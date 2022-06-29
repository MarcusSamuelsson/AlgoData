/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-20
Problem approached: Lab 2 - Sorting, problem 3

    The time complexity of inversions is (constant*O(1) + O(N^2) = O(N^2))
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class Insertionsort3 {
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

    static private void SortArray(int[] array) {
        int holding;    //Holds the element currently being moved
        int swaps = 0;  //Number of swaps being performed

        //Goes from the second int in the array
        for (int i = 1; i < array.length; i++) {
            int x = i - 1;  //The previous index in the array
            holding = array[i];

            //While the number behind the one currently in hold is lower
            while (x >= 0 && array[x] > holding) {
                array[x + 1] = array[x];    //Move bigger number to the right
                swaps++;    //Increase number of swaps
                x--;    //Decrease x to check next number to the left
            }
            array[x + 1] = holding; //Put holding infront of the smaller number

            PrintArray(array);
        }

        StdOut.println("Total number of swaps: " + swaps);
    }

    //Goes though the array to check how many inversions will be necessary and on which index
    static private void Inversions(int[] array) {
        int invAmount = 0;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    StdOut.println("[" + i + ", " + array[i] + "], " + "[" + j + ", " + array[j] + "]");
                    invAmount++;
                }
            }
        }
    }

    public static void main(String[] args) {
        StdOut.print("Enter the amount of inputs: ");
        int arraySize = StdIn.readInt();    //Takes in an integer for the array length
        int num[] = new int[arraySize];     //Creates an array with the length specified with arraySize

        //Lets user input the values in the array
        for (int i = 0; i < arraySize; i++) {
            StdOut.print("Enter input " + (i + 1) + ": ");
            num[i] = StdIn.readInt();
        }

        Inversions(num);
        SortArray(num);
    }
}
