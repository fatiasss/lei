# BD: Guião 7


## ​7.2 
 
### *a)*

```
A relação encontra-se na primeira forma normal. Não se considera na 2FN pois tem dependências parciais.
```

### *b)* 

```
1FN 
Livro (Titulo_Livro, Nome_Autor, Afiliacao_Autor, Tipo_Livro, Preco, NoPaginas,
Editor, Endereco_Editor, Ano_Publicacao)

-------------------------------------------------------------

2FN
Livro (Titulo_Livro, Nome_Autor, Tipo_Livro, Preco, NoPaginas,
Editor, Endereco_Editor, Ano_Publicacao)

Autor_Info(Nome_Autor, Afiliacao_Autor)

-------------------------------------------------------------

3FN
Livro (Titulo_Livro, Nome_Autor, Tipo_Livro, NoPaginas, Editor, Ano_Publicacao)

Price_Info(Tipo_Livro, NoPaginas, Preco)

Autor_Info(Nome_Autor, Afiliacao_Autor)

Editor_Info (Editor, Endereço_Editor)

-------------------------------------------------------------

```




## ​7.3
 
### *a)*

```
Chave : {A,B}
```

        
### *b)* 

```
1FN
R={A,B,C,D,E,F,G,H,I,J}

-------------------------------------------------------------
2FN
R={A,B,C}

R2={A,D,E}, em que A é a PK

R3={B,F}, em que B é a PK

R4={D,I,J}, em que D é a PK

R5={F,G,H}, em que F é a PK


```


### *c)* 

```
3FN
R={A,B,C}

R2={A,D,E}, em que A é a PK

R3={B,F}, em que B é a PK

R4={D,I,J}, em que D é a PK

R5={F,G,H}, em que F é a PK

Neste caso, a segunda forma normal e a terceira forma normal são idênticas. A segunda forma normal, que implica a remoção das depêndencias parciais, neste caso, obriga também à remoção das dependências transitivas. Dado que todas as dependências transitivas, são de chaves que demonstram uma dependência parcial, têm também que ser removidas da Relação R, uma vez que não poderiam ficar nesta relação se as chaves de que dependem foram removidas.

```


## ​7.4
 
### *a)*

```
Chave : {A,B}
```


### *b)* 

```
R1 = {A,B,D} e R2 = {D,E} e R3 = {A,B,C} e R3 = {C,A} ou R1 = {A,B,C,D} e R2{D,E} e R3{C,A}

```


### *c)* 

```
R1 = {A,B,D} e R2 = {D,E} e R3 = {C,A} No entanto, perde-se {A,B}-> {C} para manter o BCNF.
```



## ​7.5
 
### *a)*

```
Chave : {A,B}
```

### *b)* 

```
R1 = {A,B,D,E} e R2 = {A,C} ou R1{A,B,E} e R2 ={A,C,D}
```


### *c)* 

```
R1{A,B,E} e R2 ={A,C} e R3 = {C,D}
```

### *d)* 

```
R1{A,B,E} e R2 ={A,C} e R3 = {C,D}
```
