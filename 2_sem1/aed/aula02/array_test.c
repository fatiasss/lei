#include <stdio.h>
#include <stdlib.h>

void DisplayArray(double* a, size_t n){
    unsigned int z = 0;
    if(a==NULL||n< z) return;
    printf("Array : [ ");
    for(unsigned int i =0; i<n; i++){
        printf("%.2f ", a[i]);
    }
    printf("]\n");

}

double* ReadArray(size_t* size_p){
    if(size_p==NULL || *size_p==0) return 0;
    size_t numberOfELements = (*size_p)/sizeof(double);
    if (numberOfELements==0) return 0;
    double* array = malloc(*size_p);
    for(size_t i=0; i<numberOfELements; i++){
        printf("Enter a value for the array");
        scanf("%lf", &array[i]);
    } 
    DisplayArray(array, numberOfELements);

    return array;
    

}

double* Append(double* array_1, size_t size_1, double* array_2, size_t size_2){
    if(array_1==NULL || array_2==NULL || size_1<=0 || size_2<=0) return 0;
    double* retArray = malloc(((size_1) + (size_2))*sizeof(double));
    for(unsigned int i=0; i<size_1; i++){
        retArray[i] = array_1[i];
    }
    for(unsigned int i=0; i<size_2; i++){
        retArray[size_1+i] = array_2[i];
    }
    DisplayArray(retArray, size_1+size_2);
    return retArray;
}




int main(void){
    double array_1[5] = {1.00, 2.00, 3.00, 4.00, 5.00};
    double* ptr = array_1;
    DisplayArray(ptr, 5);
    
    size_t size = 50;
    double* array_2 = ReadArray(&size);
    size_t numberOfElements2 = size / sizeof(double);

    double* result = Append(array_1, 5, array_2, numberOfElements2);

    free(array_2);
    free(result);

    return 0;
}
