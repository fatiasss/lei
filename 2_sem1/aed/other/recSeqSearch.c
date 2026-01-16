#include "stdio.h"

int SeqSearch(int* array, int number, int currentindex, int arraySize){

    if(currentindex>arraySize-1) return -1;
    if(*array==number) return currentindex;

    return SeqSearch(array+1, number, currentindex+1, arraySize);
}


int main(int argc, char const *argv[])
{
    int array[11] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    printf("number at: %d\n", SeqSearch(array, 3, 0, 11));
    return 0;
}
