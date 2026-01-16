#include "stdio.h"

long int FibCache[100];

long fibN(int n){
    if(FibCache[n]!= -1) return FibCache[n];

    FibCache[n]=fibN(n-1)+fibN(n-2);

    return FibCache[n];
}

long fibNDinamic(int n){

    int array[n+1];

    array[0]=0;
    array[1]=1;

    for(int i=2; i<=n; i++){
        array[i]=array[i-1]+array[i-2];
    }

    return array[n];
}


int main(int argc, char const *argv[])
{
    for(int i=0; i<100; i++){
        FibCache[i]=-1;
    }
    FibCache[0]=0;
    FibCache[1]=1;
    printf("the number is: %ld\n",fibNDinamic(34));
    return 0;
}



