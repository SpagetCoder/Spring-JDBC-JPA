package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "institutes")
public class InstituteJPA
{
    @Id
    @Column(name = "INSTITUTE_ID")
    private int instituteId;

    @Column(name = "INSTITUTE_NAME")
    private String instituteName;

    @OneToMany(mappedBy = "instituteJPA")
    private List<MajorJPA> majors = new ArrayList<>();

    public InstituteJPA(String instituteName)
    {
        this.instituteName = instituteName;
    }

    public InstituteJPA()
    {

    }

    public int getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(int instituteId) {
        this.instituteId = instituteId;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public List<MajorJPA> getMajors() {
        return majors;
    }

    public void setMajors(List<MajorJPA> majors) {
        this.majors = majors;
    }

    @Override
    public String toString() {
        return "InstituteJPA{" +
                "instituteId=" + instituteId +
                ", instituteName='" + instituteName + '\'' +
                ", majors=" + majors +
                '}';
    }
}
