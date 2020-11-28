package com.example.demo;

import com.example.demo.entity.Project;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Timestamp;
import java.util.List;

public class ProjectDao extends JdbcDaoSupport
{
    public int getCount()
    {
        return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM PROJECTS",Integer.class);
    }

    public Project getOne(int id)
    {
        return getJdbcTemplate().queryForObject("SELECT * FROM PROJECTS WHERE PROJECT_ID = ?",new Object[]{id},MAPPER);
    }

    public String getNameById(int id){
        return getJdbcTemplate().queryForObject("SELECT PROJECT_NAME FROM PROJECTS WHERE PROJECT_ID = ?",new Object[]{id},String.class);
    }

    public List<Project> getProjectByName(String name)
    {
        return getJdbcTemplate().query("SELECT * FROM PROJECTS WHERE PROJECT_NAME = ?", new Object[]{name}, MAPPER);
    }

    public static final RowMapper<Project> MAPPER = (r, i) -> {

        Project project = new Project();

        project.setProjectId(r.getInt("PROJECT_ID"));
        project.setProjectName(r.getString("PROJECT_NAME"));
        project.setStartDate(r.getTimestamp("START_DATE"));
        project.setEndDate(r.getTimestamp("END_DATE"));
        project.setManagerId(r.getInt("MANAGER_ID"));
        project.setParentProjectId(r.getInt("PARENTPRO_ID"));

        return project;
    };

    public void create(Project project)
    {
        getJdbcTemplate().update("insert into projects(PROJECT_ID,PROJECT_NAME,START_DATE,END_DATE,MANAGER_ID,PARENTPRO_ID)"
                        + "values(?,?,?,?,?,?)",
                project.getProjectId(),
                project.getProjectName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getManagerId(),
                project.getParentProjectId());
    }

    public boolean delete(int id)
    {
        return getJdbcTemplate().update("DELETE FROM projects WHERE PROJECT_ID = ?", id) == 1;
    }

}
