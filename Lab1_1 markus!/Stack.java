package Lab1;


/*


Author: Markus Hedelin
Date, last updated: 2020-08-28
Problem approached: Lab 1 - The Fundamentals, problem 2, ADT in Java

Creating a stack class.

*/

import java.util.NoSuchElementException;


public class Stack {
	
	private class Node {									//stacken bygger på en länkad lista med noder, men istället för generic types använder jag bara char
		char c;				//lagrad typdata
		Node next;			//referens till nästa nod
	}
	
	private Node first = new Node();			
	
	
	public Stack() {					//Initierar stacken, first-data finns inte än
		first = null;
	}
	
	public boolean isEmpty() {			//Kollar om stacken är tom genom att kolla om första elementet finns
	    return first == null;
	}
	
	public void push(char c) {			//"Pushar" stacken, dvs. lägger till en char i stacken.
			Node oldfirst = first;		//Ny referens som pekar på gamla översta elementet i stacken
	        first = new Node();			//Ny nod till det senaste tillagda elementet
	        first.c = c;				//Den nya first (nya översta elementet) kopierar typdatan från argumentet
	        first.next = oldfirst;		//Översta elementet "nästa"-referens refererar till det gamla översta elementet - med andra ord, elementet under det nya
    }
   
	public char pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");		//Finns det inga element kvar att returnera i stacken visas felmeddelande
      
		char c = first.c;       	//Kopierar värdet/datan från översta noden
		first = first.next;         //Översta elementet byts ut till det undre, dvs. det översta värdet "ta ur stacken"
		return c;                   //Returnerar datan från elementet som tas ur stacken
   	}   
	public String toString() {								//metoden "toString" poppar stacken - tar det översta elementet - och lägger därför till chars i LIFO-ordning 
	    StringBuilder s = new StringBuilder();				//i en String. Så länge stacken inte är tom. Därför returneras elementen i omvänd ordning

	    while (!isEmpty()) {		//Så länge stacken inte är tom "poppar" vi elementen och 			
            s.append(pop());
            s.append(",");
        }
        return s.toString();
    } 
	
	
	
}
