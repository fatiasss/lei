#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

static int cmp_strptr(const void *a, const void *b) {
    const char * const *pa = (const char * const *)a;
    const char * const *pb = (const char * const *)b;
    return strcmp(*pa, *pb);
}



int main(){

    char **sorted = malloc(100*sizeof(char *));
    char nextWord[256];
    int currentIndex=0;

    while(1){
        printf("choose a word (q to quit choosing)\n");
        scanf("%255s", nextWord);
        if(!strcmp("q", nextWord)) break;
        sorted[currentIndex] = strdup(nextWord);    
        currentIndex++;
    }

    qsort(sorted, (size_t)(currentIndex), sizeof(char *), cmp_strptr);

    for(int i=0; i<currentIndex; i++){
        printf("%s\n",sorted[i]);
        free(sorted[i]);
    }

    free(sorted);
    return 0;

}