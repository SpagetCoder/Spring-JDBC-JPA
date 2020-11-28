package com.example.demo;

import com.example.demo.entity.Salary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Timestamp;
import java.util.List;

public class SalaryDao extends JdbcDaoSupport
{
    public int getCount(){return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM SALARIES", Integer.class);}

   public int getHighSalaries(int number)
   {
       return getJdbcTemplate().queryForObject("SELECT COUNT (*) FROM SALARIES WHERE AMOUNT > ? ",new Object[]{number}, Integer.class);
   }

    public List<Salary> getSalariesByProjectId(int id)
    {
        return getJdbcTemplate().query("SELECT * FROM SALARIES WHERE PROJECT_ID = ?",new Object[]{id},MAPPER);
    }

   public List<Salary> getEverything()
   {
       return getJdbcTemplate().query("SELECT * FROM SALARIES", MAPPER);
   }

    public static final RowMapper<Salary> MAPPER = (r, i) -> {

        Salary salary = new Salary();
        salary.setEmployeeId(r.getInt("EMPLOYEE_ID"));
        salary.setProjectId(r.getInt("PROJECT_ID"));
        salary.setAccountDate(r.getTimestamp("ACCOUNT_DATE"));
        salary.setPayDate(r.getTimestamp("PAY_DATE"));
        salary.setAmount(r.getLong("AMOUNT"));

        return salary;
    };

    public void create(Salary salary)
    {
        getJdbcTemplate().update("insert into salaries(EMPLOYEE_ID, PROJECT_ID, ACCOUNT_DATE, PAY_DATE, AMOUNT)"
                + "values(?,?,?,?,?)",
                salary.getEmployeeId(),
                salary.getProjectId(),
                salary.getAccountDate(),
                salary.getPayDate(),
                salary.getAmount());
    }

    public  boolean delete(int employeeId, int projectId, Timestamp ts)
    {
        return  getJdbcTemplate().update("DELETE FROM salaries WHERE EMPLOYEE_ID = ? AND PROJECT_ID = ? AND ACCOUNT_DATE = ?",
                employeeId,projectId,ts) == 1;
    }

}
