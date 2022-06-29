package Lab1;

/*

Author: Marcus Samuelsson
Date, last updated: 2020-09-01
Problem approached: Lab 1 - The Fundamentals, problem 1, recursive version

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Assignment_2_Recursive {
    public static void main(String[] args) {
        OutputBackwards(StdIn.readChar());
        StdOut.print("]");
    }


    public static void OutputBackwards(char in) {
        if (in == '\r') {    //If newline. Used to put a bracket infront of the output
            StdOut.print("[");
        } else {    //If not end of line
            OutputBackwards(StdIn.readChar());  //Calls itself
            StdOut.print(in + ", ");    //Print out current char from input
        }
    }
}
