	Drop Table FarmaciaItem;
	Drop Table Preescription;
	Drop Table Stock;
	Drop Table Farmaco;
	Drop Table Farmaceutica;
	Drop Table Farmacia;
	Drop Table Paciente;
	Drop Table Medic;
	Drop Table Specialty;


Create Table Specialty (
	ID Int Identity(1,1) PRIMARY KEY,
	[Description] varchar(500) not NULL
);

Create Table Medic(
	SNS varchar(8) Primary Key,
	Name varchar(64) not NULL,
	Specialty_ID Int not NULL Foreign KEY (Specialty_ID) REFERENCES Specialty(ID)
	);

Create Table Paciente(
	NIF INT PRIMARY KEY,
	Address varchar(128) not NULL,
	Name varchar(64) not NULL,
	Data_Nascimento Date not NULL
);

Create Table Farmacia(
	NIF INT Primary KEY,
	Adress varchar(128) not NULL,
	Name varchar(64) not NULL,
	Phone varchar(15) not NULL,
	);

Create Table Farmaceutica(
	Num_Reg_Nacional varchar(30) Primary Key,
	Name varchar(64) not NULL,
	Adress varchar(128) not NULL
);

Create Table Farmaco(
	ID int Identity(1,1) Primary Key,
	Commercial_Name varchar(32),
	Formula varchar(64),
	Farmaceutica_Num_Reg_Nacional varchar(30) not NULL, 
	Foreign Key (Farmaceutica_Num_Reg_Nacional) References Farmaceutica(Num_Reg_Nacional)
	);

Create Table Stock(
	Farmacia_NIF Int not NULL,
	Farmaco_ID Int not NULL,
	Quantidade Int not NULL,
	Primary Key (Farmacia_NIF,Farmaco_ID),
	Foreign Key (Farmacia_NIF) References Farmacia(NIF),
	Foreign Key (Farmaco_ID) References Farmaco(ID),
);

Create Table Preescription(
	ID Int Identity(1,1) Primary Key,
	Data_Processamento Date not NULL,
	Farmacia_NIF Int not NULL,
	Paciente_NIF Int not NULL,
	Medic_SNS varchar(8) not NULL,
	Foreign Key (Farmacia_NIF) References Farmacia(NIF),
	Foreign Key (Paciente_NIF) References Paciente(NIF),
	Foreign Key (Medic_SNS) References Medic(SNS)
);

Create Table FarmaciaItem(
	Farmaco_ID Int not NULL,
	Preescription_ID Int not NULL,
	Quantidade Int not NULL,
	Primary Key(Farmaco_ID,Preescription_ID),
	Foreign Key (Farmaco_ID) References Farmaco(ID),
	Foreign Key (Preescription_ID) References Preescription(ID)
);