package com.example.demo.entity;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity
@Table(name = "majors")
public class MajorJPA
{
    @Id
    @Column(name = "MAJOR_ID")
    private int majorId;

    @Column(name = "MAJOR_NAME")
    private String majorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTITUTE_ID")
    private InstituteJPA instituteJPA;

    public MajorJPA(String majorName, InstituteJPA instituteJPA) {
        this.majorName = majorName;
        this.instituteJPA = instituteJPA;
    }

    public MajorJPA()
    {

    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public InstituteJPA getInstituteJPA() {
        return instituteJPA;
    }

    public void setInstituteJPA(InstituteJPA instituteJPA) {
        this.instituteJPA = instituteJPA;
    }

    @Override
    public String toString() {
        return "MajorJPA{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", instituteJPA=" + instituteJPA +
                '}';
    }
}
