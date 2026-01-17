# BD: Guião 5


## ​Problema 5.1
 
### *a)*

```
π Pname,Pnumber project⨝Pnumber=Pno works_on⨝Essn=Ssn π Fname, Minit, Lname, Ssn employee
```
select distinct Ssn, Fname, Minit, Lname, Pname, Pnumber, Plocation, Dnum
from works_on JOIN project on Pno=Pnumber JOIN employee on Ssn=Essn


### *b)* 

```
π Fname, Minit, Lname, Super_ssn
employee⨝Super_ssn=boss.Ssn
(ρ boss (π Fname,Minit,Lname,employee.Ssn (σ(Fname='Carlos'∧Minit='D'∧Lname='Gomes')  (employee))))
```
select distinct E.Fname, E.Minit, E.Lname
from (select distinct * 
From employee
where Fname='Carlos' AND Minit='D' AND Lname='Gomes') as Boss Join employee AS E ON Boss.Ssn=E.Super_ssn

### *c)* 

```
γ Pname; sum(Hours)->TotalHours (project⨝Pnumber=Pno works_on)
```
select distinct project.Pname, SUM(works_on.Hours) AS Total_Hours_per_week
from works_on JOIN project ON Pno=Pnumber JOIN employee ON employee.Ssn=works_on.Essn
Group by project.Pname

### *d)* 

```
employee⨝ Ssn=Essn (σ works_on.Hours>20 (works_on⨝works_on.Pno=Pnumber (σ Pname='Aveiro Digital' project)))
```
select distinct *
from works_on JOIN project on Pno=Pnumber JOIN employee ON Ssn=Essn
where works_on.Hours>20 AND employee.Dno=3


### *e)* 

```
σ Essn=null (employee⟕Ssn=Essn works_on)
```
select distinct *
from employee as E left outer join works_on ON Ssn=Essn
where works_on.Essn IS NULL


### *f)* 

```
γ department.Dname; avg(employee.Salary)->avg_salary (σ employee.Sex='F' (employee⨝Dno=Dnumber department))
```
select distinct Dno, avg(Salary) AS Avg_salary_f
from employee
where Sex='F'
Group by Dno



### *g)* 

```
σ dependent_count≥2 (γ Fname, Minit, Lname, Ssn; count(employee.Ssn)-> dependent_count (employee⨝Ssn=Essn dependent))
```
select distinct *
from employee as E JOIN (select distinct employee.Ssn, count(Essn) as Dp_count
from employee JOIN dependent ON Ssn=Essn
Group by employee.Ssn) as DPE ON E.Ssn=DPE.Ssn
where DPE.Dp_count>2


### *h)* 

```
((π Man_Ssn.Ssn (ρ Man_Ssn (π Mgr_ssn department⨝Mgr_ssn=Ssn π Ssn employee)))- π Essn dependent)⨝employee
```


### *i)* 

```
π employee.Fname, employee.Minit, employee.Lname, employee.Address ((employee⨝Ssn=Essn (works_on⨝Pno=Pnumber (σ Plocation='Aveiro' project)))⨝employee.Dno=department.Dnumber (σ dept_location.Dlocation≠'Aveiro' (department⟕dept_location))
)
```
select distinct employee.Fname, employee.Minit, employee.Lname, employee.Address
from employee JOIN works_on ON Ssn=Essn JOIN project ON works_on.Pno=project.Pnumber JOIN department ON employee.Dno=department.Dnumber JOIN dept_location ON department.Dnumber=dept_location.Dnumber
Where project.Plocation='Aveiro' AND dept_location.Dlocation!='Aveiro'


## ​Problema 5.2

### *a)*

```
σ fornecedor=null (fornecedor⟕nif=fornecedor encomenda)
```

### *b)* 

```
γ codProd; avg(unidades)->avg_unidades item
```


### *c)* 

```
γ avg(prod_count)->avg_prod_count (γ numEnc; count(codProd)->prod_count item)
```


### *d)* 

```
γ fornecedor.nif, fornecedor.nome, item.codProd; sum(item.unidades)->prod_quantity_sum (((fornecedor⨝nif=fornecedor encomenda)⨝encomenda.numero=numero item)⨝item.codProd=codigo produto)
```


## ​Problema 5.3

### *a)*

```
σ prescricao.numUtente=null (paciente⟕paciente.numUtente=prescricao.numUtente prescricao)
```

### *b)* 

```
γ medico.especialidade; count(prescricao.numPresc)->count_presc (medico⨝numSNS=numMedico prescricao)
```


### *c)* 

```
γ farmacia.nome; count(prescricao.numPresc)->count_presc (farmacia⨝farmacia.nome=prescricao.farmacia prescricao)
```


### *d)* 

```
σ presc_farmaco.nomeFarmaco=null (((σ numReg=906 farmaceutica)⨝numReg=numRegFarm farmaco)⟕ farmaco.nome=nomeFarmaco presc_farmaco)
```

### *e)* 

```
γ farmaceutica.numReg, farmaceutica.nome; count(numRegFarm)->n_farmacos_vendidos (farmaceutica⨝numReg=numRegFarm farmaco)
```

### *f)* 

```
(σ count_medicos_dif≥2 (γ paciente.numUtente, prescricao.numMedico; count(prescricao.numMedico)->count_medicos_dif (prescricao⨝prescricao.numUtente=paciente.numUtente paciente)))⨝ paciente
```
