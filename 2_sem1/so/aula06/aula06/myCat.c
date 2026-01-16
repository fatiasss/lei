#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

/* SUGESTÂO: utilize as páginas do manual para conhecer mais sobre as funções usadas:
 man fopen
 man fgets
*/

#define LINEMAXSIZE 80 /* or other suitable maximum line size */


int main(int argc, char *argv[])
{
    FILE *fp = NULL;
    char line [LINEMAXSIZE]; 
    int linecounter=0;


    /* Validate number of arguments */
        for(int i=1; i<argc; i++){
            /* Open the file provided as argument */
            errno = 0;
            fp = fopen(argv[i], "r");
            if( fp == NULL )
            {
                perror ("Error opening file!");
                return EXIT_FAILURE;
            }

            /* Read all the lines of the file */
            linecounter=0;
            while( fgets(line, sizeof(line), fp) != NULL )
            {
                linecounter++;
                int len =strlen(line);
                if (line[len-1] == '\n') {
                    printf("%d -> %s", linecounter, line); /* not needed to add '\n' to printf because fgets will read the '\n' that ends each line in the file */
                } else {
                    printf("%d -> %s\n", linecounter, line);
                }
                    }

            fclose(fp);




        }
        return EXIT_SUCCESS;
   
    
}
