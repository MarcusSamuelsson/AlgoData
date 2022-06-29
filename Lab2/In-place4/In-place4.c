/*

Author: Marcus Samuelsson  
Date, last updated: 2020-09-16
Problem approached: Lab 2 - Sorting, problem 4

The time complexity of separate is (constant*O(1) + O(N) = O(N))

*/

#include <stdio.h>

//Prints out sent in array
void printArray(int *array, int size) {
    printf("[");
    for (int i = 0; i < size; i++) {
        printf("%d", array[i]);

        if (i < size - 1)
            printf(", ");   //Sets out a ',' behind every number except the last
    }
    printf("]");
}

void separate(int *array, int size) {
    int negAmount = 0;  //Number of negative numbers currently separeted
    int holding = 0;    //Number currently being moved
    
    //Goes through array
    for(int i = 0; i < size; i++) {
        if(array[i] < 0 && i == negAmount)  //If the number is negative and the index is the same as negAmount
            negAmount++;    //Add to number of negative number sorted
        else if(array[i] < 0) { //Else switch place in array with number at index x
            holding = array[negAmount];
            array[negAmount] = array[i];
            array[i] = holding;
            negAmount++;
        }
    }

    printArray(array, 10);
}

int main() {
    int numArray[10] = {1, 2, -1, 3, 4, -2, -5, 2, 4, 0};
    int numArray2[10] = {-1, -2, 1, 4, -9, 2, 5, 2, 4, -1};
    int numArray3[10] = {1, 2, 1, 3, 4, -2, -5, 2, 4, -2};

    separate(numArray, 10);
    separate(numArray2, 10);
    separate(numArray3, 10);
}