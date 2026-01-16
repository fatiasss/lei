#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
    int i;
    int total_length=1;
    for(i = 1 ; i < argc ; i++)
    {
        total_length+=strlen(argv[i]);
    }

    char* result = malloc(total_length);
    result[0] = '\0';

    for(i = 1 ; i < argc ; i++)
    {
        strcat(result, argv[i]);
    }

    printf("%s\n",result);

    return EXIT_SUCCESS;
}
