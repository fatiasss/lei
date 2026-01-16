#include <stdio.h>   
#include <stdlib.h> 
#include <assert.h> 
#include <stdbool.h>



void printResults(int results[]){
    if(results[0])printf("The array is a geometric progression! %d comparisons were made.\n", results[1]);
    else if(!results[0])printf("The array is not a geometric progression! %d comparisons were made.\n", results[1]);
}

int* checkGeometric(int array[], int arrayLength){
    assert(arrayLength > 2);
    int i=2, NumOps=0;
    int r = array[1]/array[0];
    NumOps++;
    int progGeom = 1;

    int* results = malloc(sizeof(int)*2);
    while(progGeom && i<arrayLength){
        NumOps++;
        progGeom = (array[i]==r*array[i-1]);
        i++;
    }
    results[0] = progGeom;
    results[1] = NumOps;
    return results;
}

int main(){
    int arrayToTest[] = {1,2,3,4,5,6,7,8,9,10};
    int arrayToTest2[] = {1,2,2,4,5,6,7,8,9,10};
    int arrayToTest3[] = {1,2,4,8,2,6,7,8,9,10};
    int arrayToTest4[] = {1,2,4,8,16,32,64,8,9,10};
    int arrayToTest5[] = {1,2,4,8,16,32,64,128,9,10};
    int arrayToTest6[] = {1,2,4,8,16,32,64,128,256,10};
    int arrayToTest7[] = {1,2,4,8,16,32,64,128,256,512};

    printResults(checkGeometric(arrayToTest, 10));
    printResults(checkGeometric(arrayToTest2, 10));
    printResults(checkGeometric(arrayToTest3, 10));
    printResults(checkGeometric(arrayToTest4, 10));
    printResults(checkGeometric(arrayToTest5, 10));
    printResults(checkGeometric(arrayToTest6, 10));
    printResults(checkGeometric(arrayToTest7, 10));
    return 0;
}

