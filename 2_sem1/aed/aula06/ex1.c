#include <stdio.h>   
#include <assert.h> 
#include <stdlib.h> 

int** delannoyCache;


long ncall = 0;
long arraycall = 0;
long arrayInsert = 0;
long nadd = 0;
long long** delannoyArray;


void fillDelannoyArray(int* coords){
    for(int m=0; m<=coords[0]; m++){
        for(int n=0; n<=coords[1]; n++){
            if(m==0 || n==0){
                delannoyArray[m][n]=1; 
                delannoyCache[m][n]=1;
            } 
            else delannoyArray[m][n]=-1; delannoyCache[m][n]=-1;
        }
    }
}

long calcDelannoyRecursive(int* coords){
    assert(coords[0]>=0 && coords[1]>=0);
    ncall++;

    if(coords[0]==0 || coords[1]==0) return 1;

    int coordsArray1[2] = {coords[0]-1,coords[1]};
    int coordsArray2[2] = {coords[0]-1,coords[1]-1};
    int coordsArray3[2] = {coords[0],coords[1]-1};

    nadd+=2;
    return calcDelannoyRecursive(coordsArray1) + calcDelannoyRecursive(coordsArray2) + calcDelannoyRecursive(coordsArray3);
}

long calcDelannoyDynamic(int* coords){
    assert(coords[0]>=0 && coords[1]>=0);
    int m = coords[0];
    int n = coords[1];
    ncall++;
    if(m==0 || n==0) return 1;

    if(delannoyArray[m][n] != -1) return delannoyArray[m][n];
    
    int coordsArray1[2] = {m-1,n};
    int coordsArray2[2] = {m-1,n-1};
    int coordsArray3[2] = {m,n-1};
    nadd+=2;
    delannoyArray[m][n] = calcDelannoyDynamic(coordsArray1) + calcDelannoyDynamic(coordsArray2) + calcDelannoyDynamic(coordsArray3);
    delannoyCache[n][m] = delannoyCache[m][n];
    return delannoyArray[m][n];
}


long long calcDelannoyDynamicBottomUp(int* coords){
    assert(coords[0]>=0 && coords[1]>=0);
    ncall++;

    for(int m=0; m<=coords[0]; m++){
        for(int n=0; n<=m && n<=coords[1]; n++){
            if(m==0 || n==0) {
                arrayInsert+=1;
                delannoyArray[m][n] = 1; 
            }else{
                nadd+=2;
                arraycall+=3;
                arrayInsert+=1;
                delannoyArray[m][n] = delannoyArray[m-1][n] + delannoyArray[m][n-1] + delannoyArray[m-1][n-1];
            }
            if(n!=m && m<=coords[1]) {
                arraycall++; 
                arrayInsert+=1; 
                delannoyArray[n][m] = delannoyArray[m][n];
            }
            
        }
    };

    return delannoyArray[coords[0]][coords[1]];
};

long calcDelannoyDynamicCache(int* coords){
    assert(coords[0]>=0 && coords[1]>=0);
    int m = coords[0];
    int n = coords[1];
    ncall++;
    if(m==0 || n==0) return 1;

    if(delannoyCache[m][n] != -1) return delannoyCache[m][n];
    
    int coordsArray1[2] = {m-1,n};
    int coordsArray2[2] = {m-1,n-1};
    int coordsArray3[2] = {m,n-1};
    nadd+=2;
    delannoyCache[m][n] = calcDelannoyDynamic(coordsArray1) + calcDelannoyDynamic(coordsArray2) + calcDelannoyDynamic(coordsArray3);
    delannoyCache[n][m] = delannoyCache[m][n];
    return delannoyCache[m][n];
}




int main(){
    int coordsArray[2] = {100,100};

    delannoyArray = malloc((coordsArray[0]+1) * sizeof(long long*));
    for(int i=0; i<=coordsArray[0]; i++){
        delannoyArray[i] = malloc((coordsArray[1]+1) * sizeof(long long));
    }
    arraycall=0;
    long long calculatedDelanoyDynamicBottomUp = calcDelannoyDynamicBottomUp(coordsArray);
    printf("Delanoy number calculated DYNAMICALLY to: %lld\n", calculatedDelanoyDynamicBottomUp);
    printf("Number of calls: %ld\n", ncall);
    printf("Number of Additions: %ld\n", nadd);
    printf("Number of Array Calls: %ld\n", arraycall);
    printf("Number of Array Inserts: %ld\n", arrayInsert);
    ncall=0;
    nadd=0;
    for(int i=0; i<=coordsArray[0]; i++){
        free(delannoyArray[i]);
    }
    free(delannoyArray);




    /*long calculatedDelanoyCache = calcDelannoyDynamicCache(coordsArray);
    printf("Delanoy number calculated CACHE to: %d\n", calculatedDelanoyCache);
    printf("Number of calls: %d\n", ncall);
    printf("Number of Additions: %d\n", nadd);
    ncall=0;
    nadd=0;
    for(int i=0; i<=coordsArray[0]; i++){
        free(delannoyCache[i]);
    }
    free(delannoyCache);*/
    return 0;
}