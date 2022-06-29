/*

Author: Marcus Samuelsson  
Date, last updated: 2020-09-23
Problem approached: Lab 3 - Searching, problem 1

*/

#include <stdio.h>
#include <string.h>

int main(int argc, char const *argv[])
{
    char c;

    freopen("input.txt", "r", stdin);   //Sets so stdin reads from input.txt
    freopen("output.txt", "w", stdout); //Sets so stdout prints to output.txt

    while((c = getchar()) != EOF)
        putchar(cleanText(c));

    return 0;
}

int cleanText (int ch) {
    if(isalpha(ch) == 0 || ch == ' ' || ch == '\n') //Check if ch is not a character of the alphabet, a blankspace or newline
        ch = ' ';

    return ch;
}
