#include <stdio.h>
#include <math.h>
double degrees_to_radian(double deg);

int main( void ){
    int min, max, interval;
    puts( "Enter the min, max and interval" );
    scanf("%d %d %d", &min, &max, &interval);
    printf("%-6s | %-10s | %-12s\n", "ang", "sin", "cos");
    puts("--------------------------------");
    for(int i=min; i<max; i+=interval){
        double rad = degrees_to_radian(i);
        printf("%-6d | %-10.6f | %-12.6f\n", i, sin(rad), cos(rad));
    
    }
    return 0;
}

double degrees_to_radian(double deg)
{   double calc = deg*M_PI;
    return (calc/180.0);
}
