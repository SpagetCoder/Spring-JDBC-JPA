package com.example.demo;

import java.util.List;


import com.example.demo.entity.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class EmployeesDao extends JdbcDaoSupport {

	public int getCount()
	{
		return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM EMPLOYEES", Integer.class);
	}

	public Employee getOne(int id)
	{
		return getJdbcTemplate().queryForObject("SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?", new Object[]{id}, MAPPER);
	}

	public List<Employee> getEveryoneFromTeamId(int id)
	{
		return getJdbcTemplate().query("SELECT * FROM EMPLOYEES WHERE TEAM_ID = ?", new Object[]{id}, MAPPER);
	}

	public int getFemaleCount()
	{
		return getJdbcTemplate().queryForObject("SELECT COUNT (*) FROM EMPLOYEES WHERE GENDER = 'F'", Integer.class);
	}

	public static final RowMapper<Employee> MAPPER = (r, i) ->
	{

		Employee employee = new Employee();
		employee.setEmployeeId(r.getInt("EMPLOYEE_ID"));
		employee.setEmpName(r.getString("EMP_NAME"));
		employee.setTeamId(r.getInt("TEAM_ID"));
		employee.setGender(r.getString("GENDER"));
		employee.setDateOfBirth(r.getTimestamp("DATE_OF_BIRTH"));

		return employee;
	};

	public void create(Employee employee){
		getJdbcTemplate().update("insert into employees(EMPLOYEE_ID,GENDER,DATE_OF_BIRTH,EMP_NAME,TEAM_ID) "
						+ "values(?,?,?,?,?)",
				employee.getEmployeeId(),
				employee.getGender(),
				employee.getDateOfBirth(),
				employee.getEmpName(),
				employee.getTeamId());
	}

	public boolean delete(int id)
	{
		return getJdbcTemplate().update("DELETE FROM employees WHERE EMPLOYEE_ID = ?", id) == 1;
	}

}
