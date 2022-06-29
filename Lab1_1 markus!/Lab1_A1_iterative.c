/*

Author: Markus Hedelin
Date, last updated: 2020-08-26
Problem approached: Lab 1 - The Fundamentals, problem 1, iterative version

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

#include <stdio.h>

int main() {
    int k = 5;      // Input line / array length
    int inputChar;
    int charLine[k];
    int index = k;

    while ( (inputChar = getchar()) != EOF && index > 0 ) //Samlar chars från stdin till end of file eller new line.
        charLine[--index] = inputChar;  //index börjar samma värde som k, men minskas med 1 innan värdet ändras

    while ( index < k )
        printf("%c", charLine[index++]); //Här skrivs värdena ut först och sen inkrementeras index

    return 0;
}
