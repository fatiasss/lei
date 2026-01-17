Create Table Cliente(
	NIF Int PRIMARY KEY	,
	Nome varchar(64),
	Endereco varchar(64),
	NumeroCarta varchar(10));

Create Table Balcao(
	Numero Int Identity(1,1) PRIMARY KEY,
	Endereco varchar(64) not NULL,
	Nome varchar(64) not NULL);

Create Table Tipo_Veiculo(
	Codigo Int Identity(1,1) PRIMARY KEY,
	ArCondicionado Bit,
	Designacao varchar(225));
GO 

Create Table Veiculo(
	Matricula varchar(9) PRIMARY KEY,
	Marca varchar(15),
	Ano Int,
	Tipo_Veiculo_Codigo Int not NULL FOREIGN KEY (Tipo_Veiculo_Codigo) REFERENCES Tipo_Veiculo);

GO
Create Table Aluguer(
	Numero INT Identity(1,1) Primary KEY,
	Duracao INT NOT NULL, 
    [Data] DATE NOT NULL,    
    Balcao_Num INT NOT NULL,
    FOREIGN KEY (Balcao_Num) REFERENCES Balcao(Numero),    
    Cliente_NIF Int NOT NULL, 
    FOREIGN KEY (Cliente_NIF) REFERENCES Cliente(NIF),   
    Veiculo_Matricula VARCHAR(9) NOT NULL,
    FOREIGN KEY (Veiculo_Matricula) REFERENCES Veiculo(Matricula));

Create Table Similaridade(
Tipo_Veiculo_Codigo1 Int not NULL, 
FOREIGN KEY (Tipo_Veiculo_Codigo1) References Tipo_Veiculo(Codigo),

Tipo_Veiculo_Codigo2 Int not NULL, 
FOREIGN KEY (Tipo_Veiculo_Codigo2) References Tipo_Veiculo(Codigo)
);
GO

Create Table Pesado(
Tipo_Veiculo_Codigo Int not NULL, 
FOREIGN KEY (Tipo_Veiculo_Codigo) References Tipo_Veiculo(Codigo),
Peso Int not NULL,
Passageiros Int
);
GO
Create Table Ligeiro(
Tipo_Veiculo_Codigo Int not NULL, 
FOREIGN KEY (Tipo_Veiculo_Codigo) References Tipo_Veiculo(Codigo),
Portas Int not NULL,
NumLugares Int,
Combustivel varchar(15)
);
GO