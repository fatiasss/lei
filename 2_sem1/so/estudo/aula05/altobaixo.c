#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){
    if(argc!=3){
        printf("Must only pass lower and upper limit\n");
        return EXIT_FAILURE;
    }
    int realNumber = rand() % (atoi(argv[2])-atoi(argv[1]) + 1) + atoi(argv[1]);
    int chosenNumber = -1021203;

    while(chosenNumber!=realNumber){
        printf("Guess a number!\n");
        scanf("%d", &chosenNumber);

        if(chosenNumber>realNumber){
            printf("Too high!\n");
        }
        else if (chosenNumber<realNumber){
            printf("Too low!\n");
        }
    }
    printf("That's right!\n");
    return EXIT_SUCCESS;

}