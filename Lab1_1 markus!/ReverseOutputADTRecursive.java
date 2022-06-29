package Lab1;


/*

Author: Markus Hedelin
Date, last updated: 2020-08-28
Problem approached: Lab 1 - The Fundamentals, problem 2, ADT - recursive version in Java

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

import edu.princeton.cs.algs4.*;


public class ReverseOutputADTRecursive {
	
		public static void reverseOutput (char in) {
		
		if(in == '\r') {							//Om input är en ny rad (return) har vi nått basfallet
			StdOut.print("[");						//Printar "[" för att få med den på utskriften
			return;
		}
		else {				
			reverseOutput(StdIn.readChar());		//Hoppar först in i nästa anrop till funktionen, sedan efter basfallet har nåtts printas användares char-input
			StdOut.print(in + ",");
			return;
		}
	}
		
		public static void main(String[] args) {
			
			reverseOutput(StdIn.readChar());
			StdOut.print("]");
	}
}