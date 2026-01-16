#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int compare_ascending(const void *a, const void *b) {
    return strcasecmp(*(const char **)a, *(const char **)b);
}
int compare_descending(const void *a, const void *b) {
    return strcasecmp(*(const char **)b, *(const char **)a);
}

int main(int argc, char **argv)
{
    char *order;
    int i;
    int needed_mem;

    order = getenv("SORTORDER");

    for(i = 1 ; i < argc ; i++)
    {
            needed_mem+=strlen(argv[i]); 
    }

    if(order==NULL){
        printf("Null SORTORDER env variable\n");
        return EXIT_FAILURE;
    }
    
    if(strcmp(order, "ascending") == 0){
        qsort(argv+1, (argc-1), sizeof(char*), compare_ascending);
    }
    else if(strcmp(order, "descending") == 0){
        qsort(argv+1, (argc-1), sizeof(char*), compare_descending);

    }else{
        printf("Invalid SORTORDER env variable\n");
        return EXIT_FAILURE;

    }

    for(i = 1 ; i < argc ; i++)
    {
        printf("%s\n", argv[i]);
    }
    return EXIT_SUCCESS;

        




}
