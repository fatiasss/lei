#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <ctype.h>

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
    Person p;
    Person *pArray =malloc(sizeof(Person)*100);
    int people_in_array=0;

    /* Validate number of arguments */
    if(argc != 2)
    {
        printf("USAGE: %s fileName\n", argv[0]);
        return EXIT_FAILURE;
    }

    /* Open the file provided as argument */
    errno = 0;
    fp = fopen(argv[1], "rb");
    if(fp == NULL)
    {
        perror ("Error opening file!");
        return EXIT_FAILURE;
    }

    /* read all the itens of the file */
    while(fread(&p, sizeof(Person), 1, fp) == 1)
    {
        printPersonInfo(&p);
        pArray[people_in_array]=p;
        people_in_array++;
    }
    int start=people_in_array;
    char morePeople = '\0';
    while (morePeople!='Y' && morePeople!='N'){
        printf("Add more people?Y/N\n");
        if (scanf(" %c", &morePeople) != 1) break;
        morePeople=toupper((unsigned char) morePeople);

    }
    if(morePeople=='N'){
        free(pArray);
        fclose(fp);
        return EXIT_SUCCESS;
    }

    int peopleNumber;
    printf("How many people to add?\n");
    scanf("%d", &peopleNumber);

    for(int i = 0 ; i < peopleNumber ; i++)
    {    
        char name[64];
        printf("Name?\n");
        if(scanf("%s", name) != 1) break;
        strncpy(p.name, name, sizeof p.name - 1);

        p.name[sizeof p.name - 1] = '\0';
        int age;
        printf("Age?\n");
        if(scanf("%d", &age) != 1) break;
        p.age = age;

        double height;
        printf("Height?\n");
        if(scanf("%lf", &height) != 1) break;
        p.height = height;
        
        pArray[start + i]=p;
        people_in_array++;
    };

    fp = fopen(argv[1], "ab");
    if(fp == NULL)
    {
        perror ("Error opening file!");
        return EXIT_FAILURE;
    }


    for(int k=start; k<people_in_array; k++){ 
        fwrite(&pArray[k], sizeof(Person), 1, fp);
    }
    free(pArray);

    fclose(fp);

    return EXIT_SUCCESS;
}
