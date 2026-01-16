#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

typedef struct
{
    int age;
    double height;
    char name[64];
} Person;

void printPersonInfo(Person *p)
{
    printf("Person: %s, %d, %f\n", p->name, p->age, p->height);
}

int main (int argc, char *argv[])
{
    FILE *fp = NULL;
    int i;
    Person p;
    int numPeople;

    /* Validate number of arguments */
    if(argc != 2)
    {
        printf("USAGE: %s fileName\n", argv[0]);
        return EXIT_FAILURE;
    }

    /* Open the file provided as argument */
    errno = 0;
    fp = fopen(argv[1], "wb");
    if(fp == NULL)
    {
        perror ("Error opening file!");
        return EXIT_FAILURE;
    }
    printf("How many people to add?");
    scanf("%d", &numPeople);
    /* Write 10 itens on a file */
    for(i = 0 ; i < numPeople ; i++)
    {    
        printf("Person age?");
        scanf("%d", &p.age);
        printf("Person height?");
        scanf("%lf", &p.height);
        printf("Person name?");
        scanf("%63s", p.name);

        fwrite(&p, sizeof(Person), 1, fp);
    }

    fclose(fp);

    return EXIT_SUCCESS;
}
