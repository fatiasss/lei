select distinct Dno sum(Sal,ary) AS Salary_total
from employee
group by Dno, Fname
