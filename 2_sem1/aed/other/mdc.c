#include "stdio.h"

int mdc(int a, int b){
    if(a==0) return b;
    if(b==0) return a;

    return mdc(b, a%b);
}


int main(int argc, char const *argv[])
{
    int a, b;
    a=123456;
    b=789012;
    printf("mdc = %d\n", mdc(a,b));
    return 0;
}
