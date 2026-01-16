#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

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
    Person p = {35, 1.65, "xpto"};

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

    int peopleNumber;
    printf("How many people to print?");
    scanf("%d", &peopleNumber);

    for(i = 0 ; i < peopleNumber ; i++)
    {    
        char name[64];
        printf("Name?");
        if(scanf("%s", name) != 1) break;
        strncpy(p.name, name, sizeof p.name - 1);

        p.name[sizeof p.name - 1] = '\0';
        int age;
        printf("Age?");
        if(scanf("%d", &age) != 1) break;
        p.age = age;

        double height;
        printf("Height?");
        if(scanf("%lf", &height) != 1) break;
        p.height = height;
        
        fwrite(&p, sizeof(Person), 1, fp);
    }

    fclose(fp);

    return EXIT_SUCCESS;
}
