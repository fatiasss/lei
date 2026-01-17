Create Table Conferencia_Instituicao(
	ID Int Identity(1,1) Primary Key,
	Name varchar(64) not NULL,
	Adress varchar(128) not NULL,
);

Create Table Conferencia_Autor(
	ID Int Identity(1,1) Primary Key,
	Name varchar(64) not NULL,
	Adress varchar(128) not NULL,
);

Create Table Conferencia(
	ID Int Identity(1,1) Primary Key,
	Name varchar(64) not NULL,
	Adress varchar(128) not NULL,
);

Create Table Conferencia_Artigo(
	Num_Registo varchar(12) Primary Key,
	Titulo varchar(64) not NULL,
	Conferencia_ID Int not NULL,
	Foreign Key (Conferencia_ID) References Conferencia(ID),
);

Create Table Conferencia_Autoria(
	Autor_ID Int not Null,
	Artigo_Num_Registo varchar(12) not NULL,
	Primary KEY(Autor_ID,Artigo_Num_Registo),
	Foreign Key (Autor_ID) References Conferencia_Autor(ID),
	Foreign Key (Artigo_Num_Registo) References Conferencia_Artigo(Num_Registo),
);

Create Table Conferencia_Participante(
	ID Int Identity(1,1) Primary Key,
	[Data] Date not NULL,
	Name varchar(64) not NULL,
	Email varchar(128) not NULL,
	Adress varchar(128) not NULL,
	Conferencia_ID Int not NULL,
	Instituicao_ID Int Not NULL,
	Foreign Key (Conferencia_ID) References Conferencia(ID),
	Foreign Key (Instituicao_ID) References Conferencia_Instituicao(ID),

);	

Create Table Conferencia_Nao_Estudante(
	Comprovativo_LocalizacaoEletronica varchar(128),
	Participant_ID Int not NULL  Primary Key,
	Foreign Key (Participant_ID) References Conferencia_Participante(ID)
	);

Create Table Conferencia_Estudante(
	Comprovativo_Referencia_Bancaria varchar(32),
	Participant_ID Int not NULL  Primary Key,
	Foreign Key (Participant_ID) References Conferencia_Participante(ID)
	);
	