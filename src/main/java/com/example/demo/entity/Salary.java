package com.example.demo.entity;

import java.sql.Timestamp;

public class Salary
{
    private int employeeId;
    private int projectId;
    private Timestamp accountDate;
    private Timestamp payDate;
    private Long amount;

    public Salary(int employeeId, int projectId, Timestamp accountDate, Timestamp payDate, Long amount) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.accountDate = accountDate;
        this.payDate = payDate;
        this.amount = amount;
    }

    public Salary()
    {

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Timestamp getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Timestamp accountDate) {
        this.accountDate = accountDate;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "employeeId=" + employeeId +
                ", projcetId=" + projectId +
                ", accountDate=" + accountDate +
                ", payDate=" + payDate +
                ", amount=" + amount +
                '}';
    }
}
