/*

Author: Marcus Samuelsson  
Date, last updated: 2020-09-08
Problem approached: Lab 1 - The Fundamentals, problem 1, recursive version

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

#include <stdio.h>

void ReverseInput (int inpt) {
    if(inpt != '\n') {  //check if inpt is not a new line
        ReverseInput(getchar());    //Calls itself
        putchar(inpt);
        return;
    }
    
}

int main() {
    printf("Enter a word:");
    ReverseInput(getchar());
    return 0;
}