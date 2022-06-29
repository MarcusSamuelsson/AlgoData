package Lab1;


/*


Author: Markus Hedelin
Date, last updated: 2020-08-28
Problem approached: Lab 1 - The Fundamentals, problem 2, ADT in Java

Creating a stack class.

*/

import java.util.NoSuchElementException;


public class Stack {
	
	private class Node {									//stacken bygger p� en l�nkad lista med noder, men ist�llet f�r generic types anv�nder jag bara char
		char c;				//lagrad typdata
		Node next;			//referens till n�sta nod
	}
	
	private Node first = new Node();			
	
	
	public Stack() {					//Initierar stacken, first-data finns inte �n
		first = null;
	}
	
	public boolean isEmpty() {			//Kollar om stacken �r tom genom att kolla om f�rsta elementet finns
	    return first == null;
	}
	
	public void push(char c) {			//"Pushar" stacken, dvs. l�gger till en char i stacken.
			Node oldfirst = first;		//Ny referens som pekar p� gamla �versta elementet i stacken
	        first = new Node();			//Ny nod till det senaste tillagda elementet
	        first.c = c;				//Den nya first (nya �versta elementet) kopierar typdatan fr�n argumentet
	        first.next = oldfirst;		//�versta elementet "n�sta"-referens refererar till det gamla �versta elementet - med andra ord, elementet under det nya
    }
   
	public char pop() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");		//Finns det inga element kvar att returnera i stacken visas felmeddelande
      
		char c = first.c;       	//Kopierar v�rdet/datan fr�n �versta noden
		first = first.next;         //�versta elementet byts ut till det undre, dvs. det �versta v�rdet "ta ur stacken"
		return c;                   //Returnerar datan fr�n elementet som tas ur stacken
   	}   
	public String toString() {								//metoden "toString" poppar stacken - tar det �versta elementet - och l�gger d�rf�r till chars i LIFO-ordning 
	    StringBuilder s = new StringBuilder();				//i en String. S� l�nge stacken inte �r tom. D�rf�r returneras elementen i omv�nd ordning

	    while (!isEmpty()) {		//S� l�nge stacken inte �r tom "poppar" vi elementen och 			
            s.append(pop());
            s.append(",");
        }
        return s.toString();
    } 
	
	
	
}
