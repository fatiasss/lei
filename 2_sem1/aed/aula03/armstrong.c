#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>
int* powers;
int* multiSets;
int n = 3;

int* intToArray(int number){
    int* retArray = malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        retArray[i] = (number / (int)pow(10, i)) % 10;
    }
    return retArray;
}
bool arrayContainsDigit(int* array, int digit){
    for(int i=0; i<n; i++){
        if(array[i]==digit){return true;};
    }
    return false;
}

int* generateMultiSets(){
    int setIndex = 0;
    
    for (int i = 0; i <= 9; i++) {
        for (int j = i; j <= 9; j++) {
            for (int k = j; k <= 9; k++) {
                multiSets[setIndex * n + 0] = i;
                multiSets[setIndex * n + 1] = j;
                multiSets[setIndex * n + 2] = k;
                setIndex++;
            }
        }
    }
    return multiSets;
}
int calculatePossibleArmstrong(int* set){
    int possibleArmstrong = 0;
    for(int i=0; i<n; i++){
        possibleArmstrong+=powers[set[i]];
    }
    return possibleArmstrong;
}
bool canArmstrongBeMadeFromThisSet(int* set, int possibleArmstrong){
    int* digitsArray = intToArray(possibleArmstrong);
    for(int i=0; i<n; i++){
        if(!arrayContainsDigit(set, digitsArray[i])) {
            free(digitsArray);
            return false;
        }
    }
    free(digitsArray);
    return true;
}
void findArmstrongs(int numberOfSets){
    int current_armstrong;
    for(int i=0; i<numberOfSets; i++){
        int* set = &multiSets[i*n];
        current_armstrong = calculatePossibleArmstrong(set);
        if(canArmstrongBeMadeFromThisSet(set, current_armstrong)){
            printf("%d,\n", current_armstrong);
        }
    }

}

int factorial(int n){
    int ret_fact = 1;
    int i;
    for(i = 1; i<= n; i++){
        ret_fact*=i;
    }
    return ret_fact;

}

int main(void){
    powers = malloc(10 * sizeof(int));
    for (int i = 0; i < 10; i++) {
        powers[i] = (int)pow(i, n);
    }
    int numberOfSets = factorial(n+9)/ (factorial(n)*factorial(9));
    multiSets = malloc(numberOfSets*n*sizeof(int));
    generateMultiSets();
    findArmstrongs(numberOfSets);
    free(powers);
    free(multiSets);
}