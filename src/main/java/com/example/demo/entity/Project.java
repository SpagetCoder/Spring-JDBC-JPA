package com.example.demo.entity;

import java.sql.Timestamp;

public class Project
{
    private int projectId;
    private String projectName;
    private Timestamp startDate;
    private Timestamp endDate;
    private int managerId;
    private int parentProjectId;

    public Project(int projectId, String projectName, Timestamp startDate, Timestamp endDate, int managerId, int parentProjectId)
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.managerId = managerId;
        this.parentProjectId = parentProjectId;
    }

    public Project()
    {

    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(int parentProjectId) {
        this.parentProjectId = parentProjectId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projctId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nanagerId=" + managerId +
                ", parentProjectId=" + parentProjectId +
                '}';
    }
}
