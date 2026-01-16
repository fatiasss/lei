Create Table Pessoa(
    N_CC int PRIMARY KEY,
    Nome varchar(255) NOT NULL,
    Morada varchar(255) NOT NULL,
    Data_Nascimento date NOT NULL
);

Create Table Professor(
    N_CC int NOT NULL,
    N_Funcionario int NOT NULL,
    Contacto_Telefonico varchar(13) NOT NULL,
    email varchar(255) NOT NULL,
    PRIMARY KEY (N_CC, N_Funcionario),
    FOREIGN KEY (N_CC) REFERENCES Pessoa(N_CC)
);

Create Table Adulto_Responsavel(
    N_CC int NOT NULL,
    Rel_Aluno varchar(50) NOT NULL,
    Contacto_Telefonico varchar(13) NOT NULL,
    PRIMARY KEY (N_CC, Rel_Aluno),
    FOREIGN KEY (N_CC) REFERENCES Pessoa(N_CC)
);

Create Table Encarregado_Educacao(
    N_CC int NOT NULL,
    Rel_Aluno varchar(50) NOT NULL,
    PRIMARY KEY (N_CC, Rel_Aluno),
    FOREIGN KEY (N_CC, Rel_Aluno) REFERENCES Adulto_Responsavel(N_CC, Rel_Aluno)
);

Create Table ATL(
    ID int Identity(1,1) PRIMARY KEY
);

Create Table Turma(
    ID int Identity(1,1) NOT NULL,
    ATL_ID int NOT NULL,
    Ano_Letivo varchar(10) NOT NULL,
    Designacao varchar(50) NOT NULL,
    N_max_alunos int NOT NULL CHECK (N_max_alunos>0),
    Professor_N_CC int NOT NULL,
    Professor_N_Func int NOT NULL,
    PRIMARY KEY (ATL_ID, ID),
    FOREIGN KEY (ATL_ID) REFERENCES ATL(ID),
    FOREIGN KEY (Professor_N_CC, Professor_N_Func) REFERENCES Professor(N_CC, N_Funcionario)
);

Create Table Aluno(
    N_CC int NOT NULL,
    ATL_ID int NOT NULL,
    Turma_ID int NOT NULL,
    N_CC_EE int NOT NULL,
    Rel_Aluno_EE varchar(50) NOT NULL,
    PRIMARY KEY (N_CC),
    FOREIGN KEY (N_CC) REFERENCES Pessoa(N_CC),
    FOREIGN KEY (N_CC_EE, Rel_Aluno_EE) REFERENCES Encarregado_Educacao(N_CC, Rel_Aluno),
    FOREIGN KEY (ATL_ID, Turma_ID) REFERENCES Turma(ATL_ID, ID)
);

Create Table Atividade(
    ID int Identity(1,1) PRIMARY KEY,
    Designacao varchar(50) NOT NULL,
    Custo float NOT NULL CHECK (Custo>=0)
);

Create Table Frequenta(
    Aluno_N_CC int NOT NULL,
    Atividade_ID int NOT NULL,
    PRIMARY KEY (Aluno_N_CC, Atividade_ID),
    FOREIGN KEY (Aluno_N_CC) REFERENCES Aluno(N_CC),
    FOREIGN KEY (Atividade_ID) REFERENCES Atividade(ID)
);

Create Table Disponibilidade(
    ATL_ID int NOT NULL,
    Turma_ID int NOT NULL,
    Atividade_ID int NOT NULL,
    PRIMARY KEY (ATL_ID, Turma_ID, Atividade_ID),
    FOREIGN KEY (ATL_ID, Turma_ID) REFERENCES Turma(ATL_ID, ID),
    FOREIGN KEY (Atividade_ID) REFERENCES Atividade(ID)
);

Create Table Responsabilidade_Levantar(
    Aluno_N_CC int NOT NULL,
    Adulto_N_CC int NOT NULL,
    Adulto_Rel_Aluno varchar(50) NOT NULL,
    PRIMARY KEY (Aluno_N_CC, Adulto_N_CC, Adulto_Rel_Aluno),
    FOREIGN KEY (Aluno_N_CC) REFERENCES Aluno(N_CC),
    FOREIGN KEY (Adulto_N_CC, Adulto_Rel_Aluno) REFERENCES Adulto_Responsavel(N_CC, Rel_Aluno)
);


