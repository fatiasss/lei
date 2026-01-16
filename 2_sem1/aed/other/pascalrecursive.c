#include "stdio.h"

int findC(int n, int p){
    if(p>n || n<0 || p<0) return 0;
    if(p==0) return 1;
    if(p==n) return 1;

    return findC(n-1, p-1) + findC(n-1, p);
}


int main(int argc, char const *argv[])
{
    printf("the number is: %d\n",findC(150, 11));
    return 0;
}
