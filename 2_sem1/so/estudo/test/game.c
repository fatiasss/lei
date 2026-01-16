#include <stdio.h> 
#include <stdlib.h>
#include <string.h>


#define MAXNAME 25

enum {JG = 0, GM, GS, PT};

typedef struct team {char name[MAXNAME]; int stats[4];} team;

int ranking_order(const void *px1, const void *px2){
    team *t1 = (team *)px1; //one too many *
    team *t2 = (team *)px2;
    if(t1->stats[PT]!=t2->stats[PT]){
        return (t1->stats[PT])>(t2->stats[PT]);
    }
    int saldo1 = t1->stats[GM] - t1->stats[GS];
    int saldo2 = t2->stats[GM] - t2->stats[GS];
    return saldo1>saldo2;
}

void run_match(team *table, int N){
    printf("Lista de equipas");
    for(int i=0; i<N; i++){
        printf("\t%d - %s\n", i, table[i].name);
    }
    printf("Especifique o jogo\n");
    int wintT; int loseT; int wintTscore; int loseTscore;
    printf("Equipa da casa(digite o código): "); scanf("%d", &wintT);
    printf("Equipa visitante(digite o código): "); scanf("%d", &loseT);
    printf("Jogo: %s - %s", table[wintT].name, table[loseT].name);
    printf("Resultado (digite golos de cada equipa)\n");
    printf("%s:  ", table[wintT].name); scanf("%d", &wintTscore);
    printf("%s:  ", table[loseT].name); scanf("%d", &loseTscore);

    if(wintTscore==loseTscore){
        table[wintT].stats[PT]+=1;
        table[loseT].stats[PT]+=1;
    }else if(wintTscore>loseTscore){
        table[wintT].stats[PT]+=3;
    }
    else{
        table[loseT].stats[PT]+=3;
    }
    table[wintT].stats[JG]+=1;
    table[loseT].stats[JG]+=1;

    table[wintT].stats[GM]+=wintTscore;
    table[wintT].stats[GS]+=loseTscore;

    table[loseT].stats[GS]+=wintTscore;
    table[loseT].stats[GM]+=loseTscore;
}

void print_table(team *table, int N){
    team *newtable = table;

    qsort(newtable, N, sizeof(newtable[0]), ranking_order);

    printf("%20s, %5s, %5s, %5s, %5s\n", "Equipa", "J", "GM", "GS", "PTS");

    for(int i=0; i<N; i++){
        printf("%20s, %5d, %5d, %5d, %5d\n", newtable[i].name, newtable[i].stats[JG], newtable[i].stats[GM], newtable[i].stats[GS], newtable[i].stats[PT]);
    }

}

int main(int argc, char **argv){
    if(argc!=2){
        perror("Deve dar input num ficheiro de texto!");
        exit(1);
    }
    FILE* fp = fopen(argv[1], "r");
    if(fp == NULL){
        perror("Não foi possível abrir o ficheiro!"); //no need for fp close
        exit(1);
    }
    int N=0; char linha[25];

    while(fgets(linha, sizeof(linha),fp)!=NULL){
        N++;
    }
    team *table = malloc(sizeof(team)*N);
    rewind(fp);
    int i=0;
    while(fgets(linha, sizeof(linha),fp)!=NULL){
        size_t len = strlen(linha);
        if (len && linha[len-1] == '\n') linha[len-1] = '\0';
        strncpy(table[i].name, linha, MAXNAME - 1);
        table[i].name[MAXNAME - 1] = '\0';

        table[i].stats[JG]=0;table[i].stats[GM]=0;table[i].stats[GS]=0;table[i].stats[PT]=0;
        i++;
    }
    int choice=0, term=0;
    printf("**Gestão de torneio de futebol**\n");
    while(!term){
        printf("1) Inserir resultado de jogo\n");
        printf("2) Ver tabela classificativa\n");
        printf("3) Terminar\n");
        printf("Escolha: "); scanf("%d", &choice);
        switch(choice){
            case 1: run_match(table,N); break;
            case 2: print_table(table,N); break;
            case 3: term=1;
        }
    }
}