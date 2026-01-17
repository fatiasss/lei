# BD: Guião 8


## ​8.1
 
### *a)*

```
CREATE PROCEDURE removeEmployee  
	-- Add the parameters for the stored procedure here
	@SSN int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here

	BEGIN TRY
		BEGIN TRANSACTION
	DELETE FROM Empresa_Works_On WHERE Essn = @SSN;
	DELETE FROM Empresa_Dependent WHERE Essn = @SSN;
	DELETE FROM Empresa_Employee WHERE Ssn = @SSN;
		
	COMMIT TRANSACTION SCHEDULEDELETE
	END TRY
	BEGIN CATCH
		ROLLBACK TRANSACTION SCHEDULEDELETE
	END CATCH
END
GO
```

### *b)* 

```
CREATE PROCEDURE listManagers(@OldestSsn int OUTPUT, @OldestAge int OUTPUT)
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here

	SELECT Empresa_Employee.Adress, Empresa_Employee.Bdate, Empresa_Employee.Fname, Empresa_Employee.MInit, Empresa_Employee.Lname, Empresa_Employee.Sex, Empresa_Employee.Ssn, Empresa_Employee.Super_ssn
	FROM Empresa_Employee JOIN Empresa_Department ON Empresa_Employee.Ssn=Empresa_Department.Mgr_ssn;


	WITH Oldest AS( SELECT TOP(1) Empresa_Department.Mgr_ssn AS OldestSSN, Empresa_Department.Mgr_start_date AS StartDate
	FROM Empresa_Employee JOIN Empresa_Department ON Empresa_Employee.Ssn=Empresa_Department.Mgr_ssn
	ORDER BY Empresa_Department.Mgr_start_date ASC) 
	Select @OldestSsn=Oldest.OldestSSN ,@OldestAge=DATEDIFF(YEAR, Oldest.StartDate, GETDATE()) FROM Oldest;


END
GO

```

### *c)* 

```
Create TRIGGER TR_RestrictOneManager
ON Empresa_Department
AFTER Insert, Update
	AS
	BEGIN
	SET NOCOUNT ON;

	IF EXISTS(
	SELECT d.Mgr_ssn
	From Empresa_Department as d
	Where d.Mgr_ssn In (SELECT Mgr_ssn From inserted)
	Group By d.Mgr_ssn
	Having count(*) > 1	
	)
	BEGIN
		ROLLBACK TRANSACTION;
		THROW 51000, 'Um funcionário só pode gerir 1 departamento.', 1;
		
	END
END;
GO

```

### *d)* 

```
Create TRIGGER TR_RestrictManagerRicher
ON Empresa_Employee
AFTER Insert, Update
	AS
	BEGIN
	SET NOCOUNT ON;
		
		DECLARE @LinhasAfetadas INT;

        Update e
		Set e.Salary = m.Salary-1
        FROM Empresa_Employee as e
		INNER JOIN inserted i ON e.Ssn = i.Ssn        
		INNER JOIN Empresa_Department d ON i.Dno = d.Dnumber 
		INNER JOIN Empresa_Employee m ON d.Mgr_ssn = m.Ssn   
		WHERE i.Salary >= m.Salary                   
		AND e.Ssn <> m.Ssn;   

		SET @LinhasAfetadas = @@ROWCOUNT;

    -- Se alguém foi corrigido, manda a mensagem de aviso (NÃO É ERRO)
    IF @LinhasAfetadas > 0
    BEGIN
        PRINT 'AVISO: Um ou mais salários eram superiores ao do gerente e foram ajustados automaticamente.';
    END
    
END;
GO

```

### *e)* 

```
CREATE FUNCTION dbo.getEmployeeInfo (@SSN int) RETURNS TABLE
AS
return (Select Empresa_Employee.Fname, Empresa_Employee.MInit, Empresa_Employee.Lname, Empresa_Project.Plocation
	From Empresa_Employee 
	JOIN Empresa_Works_On ON Empresa_Employee.SSN = Empresa_Works_On.Essn 
	JOIN Empresa_Project ON Empresa_Works_On.Pno = Empresa_Project.Pnumber
	WHERE Empresa_Employee.SSN=@SSN
	);
GO

SELECT * FROM dbo.getEmployeeInfo(342343434)
```

### *f)*

```
CREATE FUNCTION dbo.getAboveAverageSalaryEmployees (@DNO int) RETURNS TABLE
AS
return (Select *
	From Empresa_Employee 
	WHERE Empresa_Employee.Dno=@DNO AND Empresa_employee.Salary > (
		SELECT AVG(Salary)
		FROM Empresa_Employee
		WHERE Dno =@DNO
		)
	);
GO

SELECT * FROM dbo.getAboveAverageSalaryEmployees(2)

```

### *g)* 

```
CREATE FUNCTION dbo.employeeDeptHighAverage (@DNO int) 
RETURNS @Budgets TABLE (
    Pname VARCHAR(100),
    Pnumber INT,
    Plocation VARCHAR(100),
    Budget FLOAT,
    TotalBudget FLOAT
)
AS
BEGIN
    DECLARE @Pno INT, @Hours FLOAT, @Salary FLOAT;
    DECLARE @CurrentPno INT = -1;
    DECLARE @Budget FLOAT = 0;
    DECLARE @TotalBudget FLOAT = 0;

    -- Cursor com todos os employees e projetos
    DECLARE BudgetCursor CURSOR FAST_FORWARD FOR 
        SELECT Empresa_Project.Pnumber, Empresa_Works_On.Hours, Empresa_Employee.Salary
        FROM Empresa_Project
        JOIN Empresa_Works_On ON Empresa_Project.Pnumber = Empresa_Works_On.Pno
        JOIN Empresa_Employee ON Empresa_Works_On.Essn = Empresa_Employee.Ssn
        WHERE Empresa_Project.Dnumber = @DNO
        ORDER BY Empresa_Project.Pnumber;

    OPEN BudgetCursor;
    FETCH NEXT FROM BudgetCursor INTO @Pno, @Hours, @Salary;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @Pno != @CurrentPno AND @CurrentPno != -1 --Se passarmos para outro project, inserir a acumulação no projeto anterior
        BEGIN
            SET @TotalBudget = @TotalBudget + @Budget;
            INSERT INTO @Budgets (Pname, Pnumber, Plocation, Budget, TotalBudget)
            SELECT p.Pname, p.Pnumber, p.Plocation, @Budget, @TotalBudget
            FROM Empresa_Project p
            WHERE p.Pnumber = @CurrentPno;

            SET @Budget = 0; --Reiniciar budget
        END

        SET @Budget = @Budget + (@HOURS/40)*@SALARY;
        SET @CurrentPno = @Pno;

        FETCH NEXT FROM BudgetCursor INTO @Pno, @Hours, @Salary;
    END

    -- Inserir o ultimo projeto sem continuar o loop
    IF @CurrentPno != -1
    BEGIN
        SET @TotalBudget = @TotalBudget + @Budget;
        INSERT INTO @Budgets (Pname, Pnumber, Plocation, Budget, TotalBudget)
        SELECT Empresa_Project.Pname, Empresa_Project.Pnumber, Empresa_Project.Plocation, @Budget, @TotalBudget
        FROM Empresa_Project
        WHERE Empresa_Project.Pnumber = @CurrentPno;
    END

    CLOSE BudgetCursor;
    DEALLOCATE BudgetCursor;

    RETURN;
END
GO
```

### *h)* 

```
CREATE TRIGGER TR_Audit_Dept_After
ON Empresa_Department
AFTER DELETE
AS
BEGIN
    SET NOCOUNT ON;

    
    IF OBJECT_ID('Empresa_Department_Deleted', 'U') IS NULL
    BEGIN
       
        SELECT * INTO Empresa_Department_Deleted 
        FROM Empresa_Department 
        WHERE 1 = 0;
        
        PRINT 'Tabela Empresa_Department_Deleted criada automaticamente.';
    END

    INSERT INTO Empresa_Department_Deleted
    SELECT * FROM deleted;

    PRINT 'Departamento arquivado no histórico (AFTER Trigger).';
END;
GO

```

### *i)* 

```
Tanto as stored procedures como as UDFS apresentam as seguintes mais valias:
- Permitem a abstração da base de dados, encapsulando comportamentos e removendo a necessidade do utilizador interagir diretamente com a base de dados.
- Melhoram a performance do código.
- Facilitam a interação com a base de dados, visto que é muito mais simples chamar uma stored procedure ou uma UDF do que escrever código SQL.
- São mais seguras, uma vez que criam esta camada de interface entre o utilizador e a base de dados.
- É menos provável que um utilizador utilize a base de dados com erros de integridade de dados no seu código quando usa estas.

Têm, porém, algumas diferenças. Em termos de utilização, as stored procedures são mais adaptadas à manipulação da base de dados, enquanto as UDFs permitem principalmente a incorporação de lógica complexa em consultas.
Para além disso, stored procedures podem ou não retornar valores, enquanto UDFs retornam sempre alguma variável. Por outro lado, stored procedures permitem a alteração de permissôes, enquanto UDFs não têm essa capacidade.
Finalmente, a sintaxe de utilização difere em ambas.

```


Trabalho Realizado por:

Enrique Ornelas, 124762
João Farias, 124964
