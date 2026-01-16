#include <stdio.h>

void printArray(char *s, int a[], int size);
void cumSum(int a[], int b[], int size);

int main( void ){
    int a[12]= {31,28,31,30,31,30,31,31,30,31,30,31};
    int sizeofA = sizeof(a)/sizeof(int);
    printArray("a", a, sizeofA);
    int b[12];
    int sizeofB = sizeof(b)/sizeof(int);
    cumSum(a, b, sizeofA);
    printArray("b", b, sizeofB);
    return 0;
}

void printArray(char s[], int a[], int size){
    printf("%s + :", s);
    for(int i =0; i<size; i++){
        printf("%d ", a[i]);
    }
    printf("\n");   
}
void cumSum(int a[], int b[], int size){
    int c = 0;
    for(int i=0; i<size; i++){
        c+=a[i];
        b[i]=c;
    }
}