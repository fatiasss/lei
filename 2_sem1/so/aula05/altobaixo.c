#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <time.h>

int main(int argc, char *argv[])
{
    int try_counter=0;
    if (argc!=3){
        printf("Incorrect number of args, only accepts 2 args\n");
        return EXIT_FAILURE;
    }

    char *endptr;
    int lower_limit = strtol(argv[1], &endptr, 10);
    if (*endptr != '\0' || endptr == argv[1]) {
        printf("Invalid lower limit\n");
        return EXIT_FAILURE;
    }
    int upper_limit = strtol(argv[2], &endptr, 10);
    if (*endptr != '\0' || endptr == argv[2]) {
        printf("Invalid upper limit\n");
        return EXIT_FAILURE;
    }
    srand(time(NULL));
    int chosen_number = rand() %(upper_limit - lower_limit + 1)+lower_limit;
    int guessed_number = INT_MIN;

    while (guessed_number!=chosen_number)
    {
       printf("Guess a number!\n");
       scanf("%d", &guessed_number);
       try_counter++;
       if(guessed_number<chosen_number){
            printf("Too Low! Guess again\n");
       }
       else if(guessed_number>chosen_number){
            printf("Too High! Guess again\n");
       }
       else{
            printf("That's right! You tries %d times!\n", try_counter);
            break;
       }
    }
    return EXIT_SUCCESS;
}
