/*

Author: Markus Hedelin
Date, last updated: 2020-08-27
Problem approached: Lab 1 - The Fundamentals, problem 1, recursive version

Inputs: Fixed number of characters from user
Outputs: Prints user input characters in reverse order

*/

#include <stdio.h>

void ReverseOutput (int in) {

    if (in == '\n')
        return;
    else {
        ReverseOutput(getchar());
        putchar(in);
        return;
    }
}

int main () {
    ReverseOutput(getchar());
    return 0;
}
