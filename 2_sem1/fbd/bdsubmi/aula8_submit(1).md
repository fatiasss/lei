# BD: Guião 8


## ​8.1
 
### *a)*

```
... Write here your answer ...
```

### *b)* 

```
... Write here your answer ...
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
... Write here your answer ...
```

### *f)* 

```
... Write here your answer ...
```

### *g)* 

```
... Write here your answer ...
```

### *h)* 

```
CREATE TR_Audit_Dept_After
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
... Write here your answer ...
```
