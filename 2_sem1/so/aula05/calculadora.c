#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char *argv[])
{
    if (argc != 4){
        printf("Incorrect number of args, only accepts 3 args\n");
        return EXIT_FAILURE;
    }
    char *endptr;
    double first_number = strtod(argv[1], &endptr);
    if (*endptr != '\0' || endptr == argv[1]) {
        printf("Invalid first number\n");
        return EXIT_FAILURE;
    }
    char* operation = argv[2];
    double second_number = strtod(argv[3], &endptr);
    if (*endptr != '\0' || endptr == argv[1]) {
        printf("Invalid second number\n");
        return EXIT_FAILURE;
    }
    float result;

    switch (*operation)
    {
    case '+':
        result = first_number+second_number;
        printf("The result is: %.2f\n", result);
        return EXIT_SUCCESS;
    
    case '-':
        result = first_number-second_number;
        printf("The result is: %.2f\n", result);
        return EXIT_SUCCESS;
    
    case 'x':
        result = first_number*second_number;
        printf("The result is: %.2f\n", result);
        return EXIT_SUCCESS;

    case '/':
        result = first_number/second_number;
        printf("The result is: %.2f\n", result);
        return EXIT_SUCCESS;
    case 'p':
        result = pow(first_number,second_number);
        printf("The result is: %.2f\n", result);
        return EXIT_SUCCESS;
    
    default:
        printf("Unknown operation, shutting down\n");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}
