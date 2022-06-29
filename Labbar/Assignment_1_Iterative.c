/*

Author: Marcus Samuelsson  
Date, last updated: 2020-09-08
Problem approached: Lab 1 - The Fundamentals, problem 1, iterative version

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

#include <stdio.h>

int main() {
    int inputC;
    int charArray[6];   //Array length is max input length
    int index = 5;

    printf("Enter a word:");
    while((inputC = getchar()) != EOF && index >= 0)
        charArray[index--] = inputC;    //Fills charArray backwards
        
    while(index < 6)
        printf("%c", charArray[index++]);   //Prints charArray from index 0 to 5

    return 0;
}