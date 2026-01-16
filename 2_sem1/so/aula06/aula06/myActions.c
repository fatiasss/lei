#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>


/* SUGESTÂO: utilize as páginas do manual para conhecer mais sobre as funções usadas:
 man system
 man date
*/

int main(int argc, char *argv[])
{
    char text[1024];
    char filename[20]="command.log";
    FILE *file;
    
    do
    {
        printf("Command: ");
        scanf("%1023[^\n]%*c", text);

        /* system(const char *command) executes a command specified in command
            by calling /bin/sh -c command, and returns after the command has been
            completed.
        */
        if(strcmp(text, "end")) {
           printf("\n * Command to be executed: %s\n", text);
           printf("---------------------------------\n");
           system(text);
           printf("---------------------------------\n");
        }
        file = fopen(filename, "a");

        fprintf(file, "%s : %s\n", text, __DATE__);

        fclose(file);

    } while(strcmp(text, "end"));

    printf("-----------The End---------------\n");

    return EXIT_SUCCESS;
}
