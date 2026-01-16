#include <stdio.h>


void permute(int* a, int* b, int*c){
    int save = *b;
    *b = *a;
    *a = *c;
    *c = save;
}

int main(void){
    int a;
    int b;
    int c;
    puts( "Enter the first number" );
    scanf("%d", &a);
    puts( "Enter the second number" );
    scanf("%d", &b);
    puts( "Enter the third number" );
    scanf("%d", &c);

    permute(&a,&b,&c);
    printf("permuted, %d %d %d\n", a,b,c);


    return 0;
}
