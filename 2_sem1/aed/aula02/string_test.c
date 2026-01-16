#include <string.h>
#include <stdio.h>
#include <ctype.h>
int main( void )
{
char first_string[30];
char second_string[30];
int counter = 0;
puts( "Enter the first String" );
scanf("%s", first_string);
puts( "Enter the second String" );
scanf("%s", second_string);

for(unsigned int i = 0; i<strlen(first_string); i++){
    if(isalpha(first_string[i])) counter++;
}
printf("The first string has %d alphabetic characters \n", counter);
counter=0;
for(unsigned int i = 0; i<strlen(second_string); i++){
    if(isupper(second_string[i])) counter++;
}
printf("The second string has %d uppercase characters \n", counter);
counter=0;

for(unsigned int i = 0; i<strlen(first_string); i++){
    if(isupper(first_string[i])) first_string[i]+=32;
}
for(unsigned int i = 0; i<strlen(second_string); i++){
    if(isupper(second_string[i])) second_string[i]+=32;
}

printf("The strings in lowercase: %s\n%s\n", first_string, second_string);

int cmp = strcmp(first_string, second_string);
if (cmp<0){
    printf("%s , the first_string is first\n", first_string);
}
else if(cmp>0){
    printf("%s , the second_string is first\n", second_string);
}
else{
    printf("The strings are equal\n");
}

char string2Copy[30];

memcpy(string2Copy, second_string, strlen(second_string)+1);

printf("String 2 copy: %s\n", string2Copy);

char concatString[2*strlen(second_string)];
memcpy(concatString, strcat(second_string,string2Copy), strlen(second_string)*2+1);
printf("Concat String: %s\n", concatString);
return 0;
}



