#include <stdio.h>   
#include <stdlib.h> 
#include <assert.h> 


void printResults(int results[]){
    printf("%d ternos foram encontrados com %d comparações!\n", results[0], results[1]);
    free(results);
}

int* checkTernos(int array[], int arrayLength){
    assert(arrayLength > 2);
    int ternoCounter = 0, comparisonCounter = 0;
    for(int i = 0; i < (arrayLength - 2); i++){
        int firstNumber = array[i];
        for(int j=i+1; j< (arrayLength-1); j++){
            int sum = firstNumber + array[j];
            for(int k=j+1; k< arrayLength; k++){
                if(array[k]==sum) ternoCounter++;
                comparisonCounter++;
            }
        }
    }
    int* results = malloc(sizeof(int)*2);
    results[0] = ternoCounter; 
    results[1] = comparisonCounter; 
    return results;
}

int main(){
    // Size 5 arrays
    int array5_1[] = {1,2,3,4,5};
    int array5_2[] = {1,1,2,3,5};
    int array5_3[] = {0,0,0,0,0};
    
    // Size 10 arrays
    int array10_1[] = {1,2,3,4,5,6,7,8,9,10};
    int array10_2[] = {1,1,2,3,5,8,13,21,34,55};
    int array10_3[] = {1,2,4,8,16,32,64,128,256,512};
    
    // Size 20 arrays
    int array20_1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    int array20_2[] = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40};
    int array20_3[] = {1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765};
    
    // Size 30 arrays
    int array30_1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
    int array30_2[] = {5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100,105,110,115,120,125,130,135,140,145,150};
    int array30_3[] = {1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,9,9,9,10,10,10};
    
    // Size 40 arrays
    int array40_1[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
    int array40_2[] = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80};
    int array40_3[] = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,18,19,19,20,20};

    // Test size 5
    printf("\n=== Testing arrays of size 5 ===\n");
    printResults(checkTernos(array5_1, 5));
    printResults(checkTernos(array5_2, 5));
    printResults(checkTernos(array5_3, 5));
    
    // Test size 10
    printf("\n=== Testing arrays of size 10 ===\n");
    printResults(checkTernos(array10_1, 10));
    printResults(checkTernos(array10_2, 10));
    printResults(checkTernos(array10_3, 10));
    
    // Test size 20
    printf("\n=== Testing arrays of size 20 ===\n");
    printResults(checkTernos(array20_1, 20));
    printResults(checkTernos(array20_2, 20));
    printResults(checkTernos(array20_3, 20));
    
    // Test size 30
    printf("\n=== Testing arrays of size 30 ===\n");
    printResults(checkTernos(array30_1, 30));
    printResults(checkTernos(array30_2, 30));
    printResults(checkTernos(array30_3, 30));
    
    // Test size 40
    printf("\n=== Testing arrays of size 40 ===\n");
    printResults(checkTernos(array40_1, 40));
    printResults(checkTernos(array40_2, 40));
    printResults(checkTernos(array40_3, 40));
    
    // Test size 1000 - dynamically allocated
    printf("\n=== Testing arrays of size 1000 ===\n");
    int n = 1000;
    int* array1000 = malloc(n * sizeof(int));
    
    // Fill with sequential numbers 1 to 1000
    for(int i = 0; i < n; i++){
        array1000[i] = i + 1;
    }
    
    printf("Testing with 1,000 elements...\n");
    printResults(checkTernos(array1000, n));
    
    free(array1000);
    
    return 0;
}

