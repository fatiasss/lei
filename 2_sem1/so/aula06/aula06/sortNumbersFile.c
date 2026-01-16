#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

int LINEMAXSIZE=80;

/* SUGESTÂO: utilize as páginas do manual para conhecer mais sobre as funções usadas:
 man qsort
*/

int compareInts(const void *px1, const void *px2)
{
    int x1 = *((int *)px1);
    int x2 = *((int *)px2);
    return(x1 < x2 ? -1 : x1 == x2 ? 0 : 1);
}

int main(int argc, char *argv[])
{
    int i=0;
    int numSize=0;
    int *numbers;
    FILE* fp;
    int linecounter;
    char line [LINEMAXSIZE];
     int tmp;

    

    fp = fopen(argv[1], "r");

    linecounter=0;
    while( fscanf(fp, "%d", &tmp) == 1 )
    {
        numSize++;
    }
    /* Memory allocation for all the numbers in the arguments */
    numbers = (int *) malloc(sizeof(int) * numSize);

    rewind(fp);

    /* Storing the arguments in the "array" numbers */
    i = 0;
    while (i < numSize && fscanf(fp, "%d", &numbers[i]) == 1) {
        i++;
    }
    fclose(fp);
    /* void qsort(void *base, size_t nmemb, size_t size, int (*compar)(const void *, const void *)); 
         The qsort() function sorts an array with nmemb elements of size size.*/
    qsort(numbers, numSize, sizeof(int), compareInts);

    /* Printing the sorted numbers */
    printf("Sorted numbers: \n");
    for(i = 0 ; i < numSize ; i++)
    {
        printf("%d\n", numbers[i]);
    }
    free(numbers);

    return EXIT_SUCCESS;
}
