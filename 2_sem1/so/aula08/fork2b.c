#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    int ret;

    printf("Antes do fork: PID = %d, PPID = %d\n", getpid(), getppid());
    if ((ret = fork()) < 0) { 
        perror("erro na duplicação do processo");
        return EXIT_FAILURE;
    }
    char* pName[6];
    if (ret > 0){
        sleep(1);
        *pName="Pai";
    } 
    else{
        *pName="Filho";
    }

    printf("Quem sou eu?\nSou o %s. Após o fork: PID = %d, PPID = %d, retorno do fork = %d\n",
           *pName, getpid(), getppid(), ret);   

    return EXIT_SUCCESS;
}
