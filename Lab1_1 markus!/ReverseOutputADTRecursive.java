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
		
		if(in == '\r') {							//Om input �r en ny rad (return) har vi n�tt basfallet
			StdOut.print("[");						//Printar "[" f�r att f� med den p� utskriften
			return;
		}
		else {				
			reverseOutput(StdIn.readChar());		//Hoppar f�rst in i n�sta anrop till funktionen, sedan efter basfallet har n�tts printas anv�ndares char-input
			StdOut.print(in + ",");
			return;
		}
	}
		
		public static void main(String[] args) {
			
			reverseOutput(StdIn.readChar());
			StdOut.print("]");
	}
}