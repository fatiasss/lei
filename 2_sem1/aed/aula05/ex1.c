#include <stdio.h>   
#include <assert.h> 

int callCounter = 0;


int T1(int n){
    callCounter++;
    assert(n>0);

    if(n==1) return 1;

    return T1(n/2) + n;

}

int T2(int n){
    callCounter++;
    assert(n>0);

    if(n==1) return 1;

    return T2(n/2)+T2((n+1)/2) + n;

}

int T3(int n){
    callCounter++;
    assert(n>0);

    if(n==1) return 1;

    if(n%2==0) return 2*T3(n/2)+n;
    else return T3(n/2)+T3((n+1)/2) + n;
}










int main(){

    printf("%-5s %-15s %-15s %-15s %-15s %-15s %-15s\n", 
           "N", 
           "1ª Função (N)", "Nº de Chamadas",
           "2ª Função (N)", "Nº de Chamadas",
           "3ª Função (N)", "Nº de Chamadas");
    printf("------------------------------------------------------------------------------------------------------\n");

    for(int i=1; i<16; i++){
        callCounter = 0;
        int result1 = T1(i);
        int calls1 = callCounter;
        
        callCounter = 0;
        int result2 = T2(i);
        int calls2 = callCounter;
        
        callCounter = 0;
        int result3 = T3(i);
        int calls3 = callCounter;
        
        printf("%-5d %-15d %-15d %-15d %-15d %-15d %-15d\n",
               i,
               result1, calls1,
               result2, calls2,
               result3, calls3);
    }



    return 0;
}