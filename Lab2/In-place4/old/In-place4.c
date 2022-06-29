/*

Author: Marcus Samuelsson  
Date, last updated: 2020-09-16
Problem approached: Lab 2 - Sorting, problem 4

*/

#include <stdio.h>

void printArray(int *array, int size) {
    printf("[");
    for (int i = 0; i < size; i++) {
        printf("%d", array[i]);

        if (i < size - 1)
            printf(", ");
    }
    printf("]");
}

void separate(int *array, int size) {
    int negAmount = 0;  //Number of negative numbers currently separeted
    int holding = 0;    //Number currently being moved

    for(int i = size-1; i > 0+negAmount; i--) {
        while(array[negAmount] < 0)
            negAmount++;

        if(array[i] < 0) {
            holding = array[negAmount];
            array[negAmount] = array[i];
            array[i] = holding;
            negAmount++;
        }
    }

    printArray(array, 10);
}

int main() {
    int arraySize = 0;
    int numArray[10] = {1, 2, -1, 3, 4, -2, -5, 2, 4, 0};

    separate(numArray, 10);
}