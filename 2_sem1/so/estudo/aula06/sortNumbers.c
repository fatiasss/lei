#include <stdio.h>
#include <stdlib.h>

/* SUGESTÂO: utilize as páginas do manual para conhecer mais sobre as funções usadas:
 man qsort
*/

#define MAXNUMSIZE 100
#define LINEMAXSIZE 50


int compareInts(const void *px1, const void *px2)
{
    int x1 = *((int *)px1);
    int x2 = *((int *)px2);
    return(x1 < x2 ? -1 : x1 == x2 ? 0 : 1);
}

int main(int argc, char *argv[])
{
    if(argc<2){
        perror("must enter a filename");
        return EXIT_FAILURE;
    }
    int i=0;
    int *numbers;
    FILE *fp;
    char line[LINEMAXSIZE];

    fp = fopen(argv[1], "r");

    while( fscanf(fp, "%80s", line) ==1 )
    {
        i++;
    }
    fclose(fp);

    fp = fopen(argv[1], "r");
    numbers = (int *) malloc(sizeof(int) * (i+1));
    i=0;
    while( fscanf(fp, "%80s", line) ==1 )
    {
        if (line[0] == '\n' || line[0] == '\0') continue; 
        numbers[i++]=atoi(line);
    }
    fclose(fp);




    /* void qsort(void *base, size_t nmemb, size_t size, int (*compar)(const void *, const void *)); 
         The qsort() function sorts an array with nmemb elements of size size.*/
    qsort(numbers, i, sizeof(int), compareInts);

    /* Printing the sorted numbers */
    printf("Sorted numbers: \n");
    for(int j = 0 ; j < i ; j++)
    {
        printf("%d\n", numbers[j]);
    }

    return EXIT_SUCCESS;
}
