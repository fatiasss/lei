#include <stdio.h>
int main( void )
{
char first_name[30];
char last_name[30];
puts( "Enter your first name" );
scanf("%s", first_name);
puts( "Enter your last name" );
scanf("%s", last_name);

printf("Hello %s %s\n", first_name, last_name);
return 0;
}

