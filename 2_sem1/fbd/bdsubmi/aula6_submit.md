# BD: Guião 6

## Problema 6.1

### *a)* Todos os tuplos da tabela autores (authors);

```
SELECT DISTINCT *
FROM authors
```

### *b)* O primeiro nome, o último nome e o telefone dos autores;

```
SELECT au_fname, au_lname, phone
FROM authors
```

### *c)* Consulta definida em b) mas ordenada pelo primeiro nome (ascendente) e depois o último nome (ascendente); 

```
SELECT au_fname, au_lname, phone
FROM authors
ORDER BY au_fname ASC, au_lname ASC
```

### *d)* Consulta definida em c) mas renomeando os atributos para (first_name, last_name, telephone); 

```
SELECT au_fname as first_name, au_lname AS last_name, phone AS telephone
FROM authors
ORDER BY au_fname ASC, au_lname ASC
```

### *e)* Consulta definida em d) mas só os autores da Califórnia (CA) cujo último nome é diferente de ‘Ringer’; 

```
SELECT au_fname as first_name, au_lname AS last_name, phone AS telephone
FROM authors
WHERE state='CA' AND au_lname!='Ringer'
ORDER BY au_fname ASC, au_lname ASC
```

### *f)* Todas as editoras (publishers) que tenham ‘Bo’ em qualquer parte do nome; 

```
SELECT DISTINCT *
FROM publishers
WHERE pub_name LIKE '%BO%'
```

### *g)* Nome das editoras que têm pelo menos uma publicação do tipo ‘Business’; 

```
SELECT DISTINCT pub_name 
FROM publishers JOIN (SELECT DISTINCT *
FROM titles
Where titles.type='business') AS selected_titles ON publishers.pub_id=selected_titles.pub_id
```

### *h)* Número total de vendas de cada editora; 

```
SELECT publishers.pub_id, pub_name, city, [state], country, total_orders
FROM (SELECT titles.pub_id, COUNT(ord_num) AS total_orders
FROM titles JOIN sales ON titles.title_id=sales.title_id
GROUP BY titles.pub_id) as ord_title JOIN publishers ON ord_title.pub_id=publishers.pub_id
```

### *i)* Número total de vendas de cada editora agrupado por título; 

```
SELECT publishers.pub_id, pub_name, city, [state], country, total_orders
FROM (SELECT titles.pub_id, titles.title_id, COUNT(ord_num) AS total_orders
FROM titles JOIN sales ON titles.title_id=sales.title_id
GROUP BY titles.title_id, titles.pub_id) as ord_title JOIN publishers ON ord_title.pub_id=publishers.pub_id

```

### *j)* Nome dos títulos vendidos pela loja ‘Bookbeat’; 

```
SELECT titles.title
FROM sales JOIN (SELECT stor_id
FROM stores
WHERE stor_name='Bookbeat') AS bk_beat_id ON sales.stor_id=bk_beat_id.stor_id JOIN titles ON sales.title_id=titles.title_id
```

### *k)* Nome de autores que tenham publicações de tipos diferentes; 

```
SELECT author_types.au_id, COUNT(author_types.au_id) AS author_types_count
FROM (SELECT titleauthor.au_id, titles.type
FROM titleauthor JOIN titles ON titles.title_id=titleauthor.title_id
GROUP BY titleauthor.au_id, titles.type) AS author_types
GROUP BY author_types.au_id
HAVING COUNT(author_types.au_id)>1
```

### *l)* Para os títulos, obter o preço médio e o número total de vendas agrupado por tipo (type) e editora (pub_id);

```
SELECT titles.type, titles.pub_id ,AVG(titles.price) AS Avg_price, COUNT(sales.ord_num) AS sales_num
FROM titles JOIN sales ON sales.title_id=titles.title_id
GROUP BY titles.type, titles.pub_id
```

### *m)* Obter o(s) tipo(s) de título(s) para o(s) qual(is) o máximo de dinheiro “à cabeça” (advance) é uma vez e meia superior à média do grupo (tipo);

```
SELECT titles.type as t_type,AVG(titles.price) AS Avg_price , MAX(titles.advance) AS max_advance
FROM titles
GROUP BY titles.type
HAVING MAX(titles.advance) > 1.5 * AVG(titles.price)
```

### *n)* Obter, para cada título, nome dos autores e valor arrecadado por estes com a sua venda;

```
SELECT titles.title, authors.au_fname + ' ' + authors.au_lname AS author, titleauthor.royaltyper AS royalty
FROM titles JOIN titleauthor ON titles.title_id=titleauthor.title_id JOIN authors ON titleauthor.au_id=authors.au_id
```

### *o)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, a faturação total, o valor da faturação relativa aos autores e o valor da faturação relativa à editora;

```
, titles.ytd_sales*titles.price AS facturacao, (titles.ytd_sales*titles.price*royalty)/100 AS auths_revenue, titles.ytd_sales*titles.price-(titles.ytd_sales*titles.price*royalty)/100  AS publisher_revenue
FROM titles JOIN titleauthor ON titles.title_id=titleauthor.title_id
```

### *p)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, o nome de cada autor, o valor da faturação de cada autor e o valor da faturação relativa à editora;

```
SELECT titles.title,titles.ytd_sales, authors.au_fname + ' ' + authors.au_lname AS author, (titles.ytd_sales*titles.price*royalty*titleauthor.royaltyper)/10000 AS auth_revenue, titles.ytd_sales*titles.price-(titles.ytd_sales*titles.price*royalty)/100  AS publisher_revenue
FROM titles JOIN titleauthor ON titles.title_id=titleauthor.title_id JOIN authors ON titleauthor.au_id=authors.au_id
ORDER BY title
```

### *q)* Lista de lojas que venderam pelo menos um exemplar de todos os livros;

```
SELECT stores.stor_name, COUNT(DISTINCT titles.title_id) AS titles_sold
FROM titles JOIN sales ON titles.title_id=sales.title_id JOIN stores ON sales.stor_id=stores.stor_id
GROUP BY stores.stor_name
HAVING COUNT(DISTINCT titles.title_id) = (SELECT COUNT(DISTINCT title_id) FROM titles)
```

### *r)* Lista de lojas que venderam mais livros do que a média de todas as lojas;

```
SELECT stores.stor_name AS Store_Name, SUM(sales.qty) AS Sales
FROM stores join sales ON stores.stor_id=sales.stor_id
GROUP BY stores.stor_name
HAVING SUM(sales.qty) > (SELECT AVG(sales_per_store)
FROM(SELECT sales.stor_id ,SUM(sales.qty) AS sales_per_store
FROM sales
GROUP BY sales.stor_id) AS ST)
```

### *s)* Nome dos títulos que nunca foram vendidos na loja “Bookbeat”;

```
SELECT *
FROM titles AS books LEFT OUTER JOIN (SELECT titles.title_id
FROM stores JOIN sales ON stores.stor_id=sales.stor_id JOIN titles ON sales.title_id = titles.title_id
WHERE stores.stor_name='Bookbeat') AS books_sold_by_bookbeat ON books.title_id=books_sold_by_bookbeat.title_id
WHERE books_sold_by_bookbeat.title_id IS NULL

```

### *t)* Para cada editora, a lista de todas as lojas que nunca venderam títulos dessa editora; 

```
SELECT DISTINCT p.pub_id, p.pub_name, s.stor_id, s.stor_name
FROM publishers p
CROSS JOIN stores s LEFT JOIN titles t ON t.pub_id = p.pub_id LEFT JOIN sales sa ON sa.title_id = t.title_id AND sa.stor_id = s.stor_id                       
WHERE sa.title_id IS NULL
ORDER BY p.pub_name, s.stor_name;

```

## Problema 6.2

### ​5.1

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_1_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_1_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
... Write here your answer ...
```

##### *b)* 

```
... Write here your answer ...
```

##### *c)* 

```
... Write here your answer ...
```

##### *d)* 

```
... Write here your answer ...
```

##### *e)* 

```
... Write here your answer ...
```

##### *f)* 

```
... Write here your answer ...
```

##### *g)* 

```
... Write here your answer ...
```

##### *h)* 

```
... Write here your answer ...
```

##### *i)* 

```
... Write here your answer ...
```

### 5.2

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_2_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_2_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
... Write here your answer ...
```

##### *b)* 

```
... Write here your answer ...
```


##### *c)* 

```
... Write here your answer ...
```


##### *d)* 

```
... Write here your answer ...
```

### 5.3

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_3_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_3_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
... Write here your answer ...
```

##### *b)* 

```
... Write here your answer ...
```


##### *c)* 

```
... Write here your answer ...
```


##### *d)* 

```
... Write here your answer ...
```

##### *e)* 

```
... Write here your answer ...
```

##### *f)* 

```
... Write here your answer ...
```
