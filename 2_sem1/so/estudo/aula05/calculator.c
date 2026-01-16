#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char *argv[])
{
    float i;

    if(argc!=4){
        printf("only 3 args allowed\n");
        return EXIT_FAILURE;
    } 

    switch (*argv[2])
    {
    case '+':
        i = atof(argv[1])+atof(argv[3]);
        break;
    case '-':
        i = atof(argv[1])-atof(argv[3]);
        break;
    case 'x':
        i = atof(argv[1])*atof(argv[3]);
        break;
    case '/':
        i = atof(argv[1])/atof(argv[3]);
        break;
    case 'p':
        i = pow(atof(argv[1]),atof(argv[3]));
        break;
    default:
        perror("must choose a valid operation: +,-,x,/,p\n");
        return EXIT_FAILURE;
    }

    printf("The result is: %g\n", i);

    return EXIT_SUCCESS;
}
