#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

static int cmp_strptr(const void *a, const void *b) {
    const char * const *pa = (const char * const *)a;
    const char * const *pb = (const char * const *)b;
    return strcmp(*pa, *pb);
}



int main(int argc, char *argv[]){

    char **sorted = malloc((argc-1)*sizeof(char *));

    for(size_t i=0; i<(size_t)(argc - 1); i++){
        sorted[i]=argv[i+1];
        for(size_t j=0; j<strlen(sorted[i])*sizeof(char); j++){
            sorted[i][j]=tolower(sorted[i][j]);
        }
    }
    qsort(sorted, (size_t)(argc - 1), sizeof(char *), cmp_strptr);

    for(int i=0; i<argc-1; i++){
        printf("%s\n",sorted[i]);
    }

    free(sorted);
    return 0;

}