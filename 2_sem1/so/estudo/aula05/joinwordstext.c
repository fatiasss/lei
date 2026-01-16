#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int MAXCHARS=100;

int main(int argc, char **argv)
{
    int i, numChars;
    char *username; 
    char *alltext= malloc(MAXCHARS*sizeof(char));

    username = getenv("NEWUSER");
    if(username != NULL)
    {
        printf("This program is being executed by %s\n", username);
    }
    else
    {
        printf("ERROR: NEWUSER not defined\n");
        return EXIT_FAILURE;
    }

    numChars = 0;
    for(i = 1 ; i < argc ; i++)
    {
        if(!isalpha(argv[i][0]))continue;
        numChars += strlen(argv[i]);
        strcat(alltext, argv[i]);
    }

    printf("All arguments have %d characters\n", numChars);
    printf("All arguments joined %s \n", alltext);

    free(alltext);

    return EXIT_SUCCESS;
}
