package com.example.demo.entity;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employees")
public class EmployeeJPA
{
    @Id
    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DATE_OF_BIRTH")
    private Timestamp dateOfBirth;

    @Column(name = "EMP_NAME")
    private String empName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "TEAM_ID")
    private TeamJPA teamJPA;

    public EmployeeJPA() {}

    public EmployeeJPA(int employeeId, String gender, Timestamp dateOfBirth, String empName, TeamJPA teamJPA)
    {
        this.employeeId = employeeId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.empName = empName;
        this.teamJPA = teamJPA;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public TeamJPA getTeamJPA() {
        return teamJPA;
    }

    public void setTeamJPA(TeamJPA teamJPA) {
        this.teamJPA = teamJPA;
    }

    @Override
    public String toString() {
        return "EmployeeJPA{" +
                "employeeId=" + employeeId +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", empName='" + empName + '\'' +
                ", teamJPA=" + teamJPA +
                '}';
    }
}
