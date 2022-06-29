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

public class Assignment_2_Iterative {
    public static void main(String[] args) {
        Stack stack = new Stack();
        char in;


        //Loops trough StdIn until endline
        while ((in = StdIn.readChar()) != '\r')
            stack.push(in);  //Pushes the char to the stack

        StdOut.println("[" + stack.toString() + "]");
    }
}
