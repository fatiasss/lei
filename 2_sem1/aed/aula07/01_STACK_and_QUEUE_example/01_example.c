//
// TO DO : desenvolva um algoritmo para verificar se um numero inteiro positivo
//         e uma capicua
//         Exemplos: 12321 e uma capiacua, mas 123456 nao e
//         USE uma PILHA DE INTEIROS (STACK) e uma FILA DE INTEIROS (QUEUE)
//
// TO DO : design an algorithm to check if the digits of a positive decimal
//         integer number constitue a palindrome
//         Examples: 12321 is a palindrome, but 123456 is not
//         USE a STACK of integers and a QUEUE of integers
//

#include <stdio.h>

#include <stdbool.h>
#include "IntegersQueue.h"
#include "IntegersStack.h"
#include "assert.h"
#include "math.h"

bool checkPalindrome(int i){
    assert(i>0);
    bool retval=true;
    int numberSize=(int)log10(i)+1;
    int n=i;
    Stack* numberStack=StackCreate(numberSize);
    Queue* numberQueue=QueueCreate(numberSize);
    while (n>0){
        int alg=n%10;
        n= n/10;
        StackPush(numberStack, alg);
        QueueEnqueue(numberQueue, alg);
    }
    for(int n=0; n<numberSize; n++){
        if(StackPop(numberStack)!=QueueDequeue(numberQueue)){
            retval=false;
        }
    }

    StackDestroy(&numberStack);
    QueueDestroy(&numberQueue);

    return retval;
    
}

int main(void) { 
    int numberToCheck=12321;
    bool isPalindrome=checkPalindrome(numberToCheck);
    if(isPalindrome){
        printf("%d is a palindrome!\n", numberToCheck);
    }
    else{
        printf("%d is not a palindrome!\n", numberToCheck);
    }
    return 0; 
}
