#include <stdio.h>
#include <math.h>
int main( void ){
    int lines;
    puts( "Enter the number of lines" );
    scanf("%d", &lines);
    printf("%-6s | %-10s | %-12s\n", "n", "n*n", "sqrt(n)");
    puts("--------------------------------");
    for(int i=0; i<lines; i++){
        printf("%-6d | %-10.0f | %-12.6f\n", i, pow(i, 2.0), sqrt(i));
    
    }
    return 0;
}