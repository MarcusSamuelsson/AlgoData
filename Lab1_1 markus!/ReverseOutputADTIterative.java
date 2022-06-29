package Lab1;


/*

Author: Markus Hedelin
Date, last updated: 2020-08-28
Problem approached: Lab 1 - The Fundamentals, problem 2, ADT - iterative version in Java

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class ReverseOutputADTIterative {
	
	
	public static void main(String[] args) {
		
		char input = 0;
		Stack stack = new Stack();
				
		while ((input = StdIn.readChar()) != '\r')  {			//Så länge input från användaren inte är en ny rad (return) "pushar" vi chars till stacken
			stack.push(input);
		}
		StdOut.print("[" + stack.toString() + "]");		
	}
}

