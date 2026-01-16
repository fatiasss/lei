#include <stdio.h>   
#include <stdlib.h> 
#include <assert.h> 


void printResults(int results[]){
    printf("%d matching numbers have been found!\n", results[0]);
    printf("%d comparisons have been made!\n", results[1]);
    free(results);
}

int* checkSumsArray(int array[], int arrayLength){
    assert(arrayLength > 2);
    int matchingCounter = 0, comparisonCounter = 0;
    for(int i = 1; i < (arrayLength - 1); i++){
        int sum = array[i - 1] + array[i + 1];
        if(sum == array[i]) matchingCounter++;
        comparisonCounter++;
    }
    int* results = malloc(sizeof(int)*2);
    results[0] = matchingCounter; 
    results[1] = comparisonCounter; 
    return results;
}

int main(){
    int arrayToTest[] = {1,2,3,4,5,6,7,8,9,10};
    int arrayToTest2[] = {1,2,1,4,5,6,7,8,9,10};
    int arrayToTest3[] = {1,2,1,3,2,6,7,8,9,10};
    int arrayToTest4[] = {0,2,2,0,3,3,0,4,4,0};
    int arrayToTest5[] = {0,0,0,0,0,0,0,0,0,0};

    printResults(checkSumsArray(arrayToTest, 10));
    printResults(checkSumsArray(arrayToTest2, 10));
    printResults(checkSumsArray(arrayToTest3, 10));
    printResults(checkSumsArray(arrayToTest4, 10));
    printResults(checkSumsArray(arrayToTest5, 10));
    return 0;
}

