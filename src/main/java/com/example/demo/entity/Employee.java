package com.example.demo.entity;


import java.sql.Timestamp;

public class Employee
{
    private int employeeId;
    private String gender;
    private Timestamp dateOfBirth;
    private String empName;
    private int teamId;

    public Employee() {}

    public Employee(int employeeId, String gender, Timestamp dateOfBirth, String empName, int teamId)
    {
        this.employeeId = employeeId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.empName = empName;
        this.teamId = teamId;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", empName='" + empName + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
