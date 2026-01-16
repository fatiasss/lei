#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_WORDS 100
#define MAX_WORD_LEN 50


int compare_ascending(const void *a, const void *b) {
    return strcasecmp(*(const char **)a, *(const char **)b);
}
int compare_descending(const void *a, const void *b) {
    return strcasecmp(*(const char **)b, *(const char **)a);
}

int main()
{
    char *order;
    int i;
    char **words;
    char word[MAX_WORD_LEN];
    int word_count= 0;

    words = malloc(MAX_WORDS * sizeof(char*));

    printf("Insert a word\n");
    order = getenv("SORTORDER");
    while(word_count < MAX_WORDS && fgets(word, MAX_WORD_LEN, stdin)){
        size_t len = strlen(word);
        if (len > 0 && word[len-1] == '\n') {
            word[len-1] = '\0';
        }
        if (strlen(word) == 0) break;
        words[word_count] = strdup(word);
        word_count++;
        printf("Add another word?\n");
    }


    if(order==NULL){
        printf("Null SORTORDER env variable\n");
        return EXIT_FAILURE;
    }
    
    if(strcmp(order, "ascending") == 0){
        qsort(words, (word_count), sizeof(char*), compare_ascending);
    }
    else if(strcmp(order, "descending") == 0){
        qsort(words, (word_count), sizeof(char*), compare_descending);

    }else{
        printf("Invalid SORTORDER env variable\n");
        return EXIT_FAILURE;

    }

    for(i = 0 ; i < word_count ; i++)
    {
        printf("%s\n", words[i]);
        free(words[i]);
    }
    free(words);
    return EXIT_SUCCESS;

        




}