#include "stdio.h"

int findbn(int b, int n){
    if(n==0) return 1;
    if(n==1) return b;

    if(n%2==0){
        return findbn(b, n/2)*findbn(b, n/2);
    }else{
        return b*findbn(b, (n-1)/2)*findbn(b, (n-1)/2);
    }
}


int main(int argc, char const *argv[])
{
    printf("the number is: %d\n",findbn(3, 10));
    return 0;
}
