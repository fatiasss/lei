#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
    int i;

    if (argv[3] || !(argv[1] && argv[2])){
        printf("Incorrect number of args, only accepts 2 args\n");
        return EXIT_FAILURE;
    }

    for(i = 1 ; i < argc ; i++)
    {
        printf("Argument %02d: \"%s\"\n", i, argv[i]);        
    }

    return EXIT_SUCCESS;
}
