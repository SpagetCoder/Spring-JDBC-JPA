package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
public class TeamJPA
{
	@Id
	@Column(name = "TEAM_ID")
	int teamId;

	@Column(name = "TEAM_NAME")
	private String teamName;

	@OneToOne
	@JoinColumn(name = "INSTITUTE_ID")
	private InstituteJPA instituteJPA;

	@OneToOne
	@JoinColumn(name = "MANAGER_ID")
	private EmployeeJPA manager;

	@OneToMany(mappedBy = "teamJPA")
	private List<EmployeeJPA> employeeJpaList = new ArrayList<>();

	public TeamJPA(String teamName, InstituteJPA instituteJPA, EmployeeJPA manager, List<EmployeeJPA> employeeJpaList) {
		this.teamName = teamName;
		this.instituteJPA = instituteJPA;
		this.manager = manager;
		this.employeeJpaList = employeeJpaList;
	}

	public TeamJPA()
	{

	}

	public EmployeeJPA getManager() {
		return manager;
	}

	public void setManager(EmployeeJPA manager) {
		this.manager = manager;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public InstituteJPA getInstituteJPA() {
		return instituteJPA;
	}

	public void setInstituteJPA(InstituteJPA instituteJPA) {
		this.instituteJPA = instituteJPA;
	}

	public List<EmployeeJPA> getEmployeeJpaList() {
		return employeeJpaList;
	}

	public void setEmployeeJpaList(List<EmployeeJPA> employeeJpaList) {
		this.employeeJpaList = employeeJpaList;
	}

	@Override
	public String toString() {
		return "TeamJPA{" +
				"teamId=" + teamId +
				", teamName='" + teamName + '\'' +
				", instituteJPA=" + instituteJPA +
				", manager=" + manager +
				", employeeJpaList=" + employeeJpaList +
				'}';
	}
}

