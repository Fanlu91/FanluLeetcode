### Combine Two Tables 

175

```sql
select p.FirstName,p.LastName,a.City,a.State
from Person p
left join Address a 
using(PersonId)
```

如果地址表中查询的所有人的地址都存在，下面这么做没有问题。但是，题目中强调了，人一定存在，但地址不一定。

```sql
select Person.FirstName, Person.LastName, Address.City, Address.State 
from Person, Address 
where Person.PersonId = Address.PersonId
```



### [Second Highest Salary](https://leetcode-cn.com/problems/second-highest-salary/)

176

```sql
SELECT Ifnull((SELECT DISTINCT salary 
               FROM   employee 
               ORDER  BY salary DESC 
               LIMIT  1, 1), NULL) AS SecondHighestSalary 
```

Ifnull( )的用法。



#### [Nth Highest Salary](https://leetcode-cn.com/problems/nth-highest-salary/)

177

```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N := N-1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT 
            salary
      FROM 
            employee
      GROUP BY 
            salary
      ORDER BY 
            salary DESC
      LIMIT N, 1
  );
END
```

排名第N高意味着要跳过N-1个薪水，由于无法直接用limit N-1，所以需先在函数开头处理N为N=N-1

不能直接用limit N-1是因为limit和offset字段后面只接受正整数（意味着0、负数、小数都不行）或者单一变量（意味着不能用表达式）。



#### [ Rank Scores](https://leetcode-cn.com/problems/rank-scores/)

178

##### 解法一

```sql
SELECT a.score                     AS score, 
       (SELECT Count(DISTINCT b.score) 
        FROM   scores b 
        WHERE  b.score >= a.score) AS `rank` 
FROM   scores a 
ORDER  BY score DESC; 
```

For MySQL solutions, to escape reserved words used as column names, you can use an apostrophe before and after the keyword. For example \`Rank\`.

效率比较低。



##### 专用窗口函数rank, dense_rank, row_number

```sql
select Score, DENSE_RANK() over
(order by Score DESC) as `rank` 
from Scores
```

dense_rank函数： 并列不占用排名 1 2 2 2 3

rank函数： 并列占用排名 1 2 2 2 5

row_number函数： 不考虑并列名次 1 2 3 4 5





