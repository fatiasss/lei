#include "stdio.h"

int findBiggest(int* array, int arraysize){

    if(arraysize==1) return *array;
    if(arraysize==2){
        if(array[0]>array[1]) return array[0];
        return array[1];
    }

    int leftMax = findBiggest(array, (arraysize+1)/2);
    int rightMax = findBiggest(array + (arraysize+1)/2, arraysize/2);
    if(leftMax > rightMax) return leftMax;
    return rightMax;
}


int main(int argc, char const *argv[])
{
    int array[11] = {0, 1, 2, 374, 4, 5, 6, 7, 8, 9, 10};
    printf("the number is: %d\n",findBiggest(array, 11));
    return 0;
}
