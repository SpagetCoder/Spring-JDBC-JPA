package com.example.demo;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public EmployeesDao employeesDAO(DataSource ds)
	{
		EmployeesDao dao = new EmployeesDao();
		dao.setDataSource(ds);
		return dao;
	}

	@Bean
	public ClassroomDao classroomDao(DataSource ds)
	{
		ClassroomDao dao = new ClassroomDao();
		dao.setDataSource(ds);
		return dao;
	}

	@Bean
	public ProjectDao projectDao(DataSource ds)
	{
		ProjectDao dao = new ProjectDao();
		dao.setDataSource(ds);
		return dao;
	}

	@Bean
	public SalaryDao salaryDao(DataSource ds)
	{
		SalaryDao dao = new SalaryDao();
		dao.setDataSource(ds);
		return dao;
	}

	@Bean
	public DataSource dataSource()
	{
		MysqlDataSource	 ds = new MysqlDataSource();
		ds.setUrl("jdbc:mysql://127.0.0.1:6033/university");
		ds.setUser("root");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
		jva.setShowSql(true); jva.setGenerateDdl(true);
		jva.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return jva;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
																	   JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPackagesToScan("com.example.demo.entity");
		emf.setPersistenceUnitName("unit");
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		return emf;
	}

	@Bean
	public TeamDaoJPA teamDaoJPA(EntityManagerFactory entityManagerFactory){
		TeamDaoJPA majorDaoJPA =new TeamDaoJPA();
		majorDaoJPA.setEmf(entityManagerFactory);
		return majorDaoJPA;
	}

	@Bean
	public MajorDaoJPA majorDaoJPA(EntityManagerFactory entityManagerFactory){
		MajorDaoJPA majorDaoJPA = new MajorDaoJPA();
		majorDaoJPA.setEmf(entityManagerFactory);
		return majorDaoJPA;
	}

	@Bean
	public EmployeesDaoJPA employeesDaoJPA(EntityManagerFactory entityManagerFactory){
		EmployeesDaoJPA employeesDaoJPA = new EmployeesDaoJPA();
		employeesDaoJPA.setEmf(entityManagerFactory);
		return employeesDaoJPA;
	}

	@Bean
	public InstituteDaoJPA instituteDAO(EntityManagerFactory entityManagerFactory){
		InstituteDaoJPA instituteDaoJPA = new InstituteDaoJPA();
		instituteDaoJPA.setEmf(entityManagerFactory);
		return instituteDaoJPA;
	}
}
