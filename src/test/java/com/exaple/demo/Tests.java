package com.exaple.demo;

import com.example.demo.*;
import com.example.demo.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=DemoApplication.class)
public class Tests {

	@Autowired
	EmployeesDao employeesDao;

	@Autowired
	ClassroomDao classroomDao;

	@Autowired
	ProjectDao projectDao;

	@Autowired
	SalaryDao salaryDao;
	
	@Autowired
	TeamDaoJPA teamDaoJPA;

	@Autowired
	EmployeesDaoJPA employeeJpaDAO;

	@Autowired
	MajorDaoJPA majorDaoJPA;

	@Autowired
	InstituteDaoJPA instituteDaoJPA;

	@Test
	public void contextLoads()
	{
		System.out.println("Count employees JDBC: " + employeesDao.getCount());
		System.out.println("Count teams JPA: " + teamDaoJPA.getCount());
	}

	@Test
	public void testEmployees()
	{
		Assert.assertEquals("getOne() works","POPKO",employeesDao.getOne(12).getEmpName());
		Assert.assertEquals("getCount works", 41, employeesDao.getCount());
		Assert.assertEquals("getFemaleCount() works",16,employeesDao.getFemaleCount());
		Assert.assertEquals("getEveryoneFromTeamId works",6,employeesDao.getEveryoneFromTeamId(3).size());

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		Employee employee = new Employee();
		employee.setEmpName("Dick");
		employee.setGender("M");
		employee.setEmployeeId(42);
		employee.setDateOfBirth(ts);
		employee.setTeamId(4);

		long number = employeesDao.getCount();

		employeesDao.create(employee);
		Assert.assertEquals("create() works",number + 1,employeesDao.getCount());
		employeesDao.delete(42);
		Assert.assertEquals("delete) works",number,employeesDao.getCount());
	}

	@Test
	public void testClassroom()
	{
		Assert.assertEquals("getOne() works",23,classroomDao.getOne(3).getRoomSize());
		Assert.assertEquals("getCount() works",16,classroomDao.getCount());
		Assert.assertEquals("getEveryRoomWithScreen() works",4,classroomDao.getEveryRoomWithScreen().size());
		Assert.assertEquals("getRoomsBiggerThan() works", 2,classroomDao.getRoomsBiggerThan(30));

		Classroom classroom = new Classroom(17,47,"Y");
		int number = classroomDao.getCount();
		classroomDao.create(classroom);
		Assert.assertEquals("create() works",number + 1,classroomDao.getCount());

		classroomDao.delete(17);
		Assert.assertEquals("delete() works",number,classroomDao.getCount());
	}

	@Test
	public void testProject()
	{
		Assert.assertEquals("getOne() works","ADA ENVIRONMENT",projectDao.getOne(1).getProjectName());
		Assert.assertEquals("getCount() works",45,projectDao.getCount());
		Assert.assertEquals("getProjectByName() works","POWER SUPPLY",projectDao.getProjectByName("POWER SUPPLY").get(0).getProjectName());
		Assert.assertEquals("getNameById() works","RESTORING",projectDao.getNameById(40));

		Project project = new Project();

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		project.setStartDate(ts);
		project.setEndDate(ts);

		project.setProjectId(109);
		project.setParentProjectId(1);
		project.setManagerId(32);


		project.setProjectName("Fabulous project");
		projectDao.create(project);
		Assert.assertEquals("create() works", "Fabulous project", projectDao.getNameById(109));
		projectDao.delete(109);
		Assert.assertEquals("delete() works",45,projectDao.getCount());
	}

	@Test
	public void testSalary()
	{
		Assert.assertEquals("getSalariesByProjectId() works",4 ,salaryDao.getSalariesByProjectId(1).size());
		Assert.assertEquals("getCount() works",204,salaryDao.getCount());
		Assert.assertEquals("getHighSalaries() works",80,salaryDao.getHighSalaries(600));

		Salary salary = new Salary();
		salary.setEmployeeId(5);
		salary.setProjectId(3);
		salary.setAmount((long)450);

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		salary.setPayDate(ts);
		salary.setAccountDate(ts);

		salaryDao.create(salary);
		Assert.assertEquals("create() works",205,salaryDao.getEverything().size());
		salaryDao.delete(5,3,ts);
		Assert.assertEquals("delete() works",204,salaryDao.getEverything().size());

	}

	@Test
	public void testEmployeeJPA()
	{
		Assert.assertEquals("getOne() works","JOHNNY",employeeJpaDAO.getOne(4).getEmpName());
		Assert.assertEquals("getCount() works", 41, employeeJpaDAO.getCount());
		Assert.assertEquals("getFemaleCount() works",16,employeeJpaDAO.getFemaleCount());
		Assert.assertEquals("getEveryoneFromTeamId() works",6,employeeJpaDAO.getEveryoneFromTeamId(1).size());

		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());

		EmployeeJPA employeeJPA = new EmployeeJPA();
		employeeJPA.setEmpName("Andrew");
		employeeJPA.setGender("M");
		employeeJPA.setDateOfBirth(ts);
		employeeJPA.setEmployeeId(42);

		TeamJPA teamJPA = new TeamJPA();
		teamJPA.setTeamId(44);
		teamJPA.setInstituteJPA(instituteDaoJPA.getOne(3));
		teamJPA.setTeamName("Fabulous team");
		teamJPA.setManager(employeeJpaDAO.getOne(3));
		teamJPA.setEmployeeJpaList(employeeJpaDAO.getEveryoneFromTeamId(1));
		employeeJPA.setTeamJPA(teamJPA);


		long number = employeeJpaDAO.getCount();

		employeeJpaDAO.create(employeeJPA);
		Assert.assertEquals("create() works",number + 1,employeeJpaDAO.getCount());
		employeeJpaDAO.delete(42);
		Assert.assertEquals("delete() works",number,employeeJpaDAO.getCount());
	}

	@Test
	public void testTeamJPA()
	{
		Assert.assertEquals("getOne() works","AUTOMATION",teamDaoJPA.getOne(5).getTeamName());
		Assert.assertEquals("getCount() works",11,teamDaoJPA.getCount());
		Assert.assertEquals("getTeamFromTheSameInstitute() works",4,teamDaoJPA.getTeamFromTheSameInstitute(1).size());
		Assert.assertEquals("getTeamByName works","DIGGER",teamDaoJPA.getTeamByName("DIGGER").get(0).getTeamName());


		TeamJPA teamJPA = new TeamJPA();
		teamJPA.setTeamId(44);
		teamJPA.setManager(employeeJpaDAO.getOne(2));
		teamJPA.setTeamName("Snowflakes");
		teamJPA.setInstituteJPA(instituteDaoJPA.getOne(1));

		long number = teamDaoJPA.getCount();

		teamDaoJPA.create(teamJPA);
		Assert.assertEquals("create() works",number + 1,teamDaoJPA.getCount());
		teamDaoJPA.delete(44);
		Assert.assertEquals("delete() works",number,teamDaoJPA.getCount());
	}

	@Test
	public void testMajorJPA()
	{
		Assert.assertEquals("getOne() works","ELECTROTECHNOLOGY",majorDaoJPA.getOne(5).getMajorName());
		Assert.assertEquals("getCount() works",10,majorDaoJPA.getCount());
		Assert.assertEquals("getMajorByName() works","SOFTWARE",majorDaoJPA.getMajorByName("SOFTWARE").get(0).getMajorName());
		Assert.assertEquals("getCount() works",3,majorDaoJPA.getMajorsFromTheSameInstitute(1).size());

		MajorJPA majorJPA = new MajorJPA();
		majorJPA.setInstituteJPA(instituteDaoJPA.getOne(1));
		majorJPA.setMajorId(44);
		majorJPA.setMajorName("Mirkokierunek");

		long number = majorDaoJPA.getCount();

		majorDaoJPA.create(majorJPA);
		Assert.assertEquals("create() works",number + 1,majorDaoJPA.getCount());
		majorDaoJPA.delete(44);
		Assert.assertEquals("delete() works",number,majorDaoJPA.getCount());
	}

	@Test
	public void testInstituteJPA()
	{
		Assert.assertEquals("getOne() works","MINING",instituteDaoJPA.getOne(5).getInstituteName());
		Assert.assertEquals("getCount() works",6,instituteDaoJPA.getCount());
		Assert.assertEquals("getInstituteName() works","MINING",instituteDaoJPA.getByInstituteName("MINING").get(0).getInstituteName());
		Assert.assertEquals("getEverything() works",6,instituteDaoJPA.getEverything().size());

		InstituteJPA instituteJPA = new InstituteJPA();

		instituteJPA.setInstituteId(44);
		instituteJPA.setInstituteName("Fabulous institute");

		long number = instituteDaoJPA.getCount();

		instituteDaoJPA.create(instituteJPA);
		Assert.assertEquals("create() works",number + 1,instituteDaoJPA.getCount());
		instituteDaoJPA.delete(44);
		Assert.assertEquals("delete() works",number,instituteDaoJPA.getCount());
	}



}
