package staaankey.group.accountingsalaries.educations.model;

import jakarta.persistence.*;


@Entity(name = "educations")
public class Education {
    @Id
    @Column(name = "education_id")
    private int id;
    private String degree;
    @Column(name = "diploma_number")
    private int diplomaNumber;
    @Column(name = "diploma_series")
    private String diplomaSeries;
    @Column(name = "university_name")
    private int universityName;


    public Education() {
    }

    public Education(int id, String degree, int diplomaNumber, String diplomaSeries, int universityName) {
        this.id = id;
        this.degree = degree;
        this.diplomaNumber = diplomaNumber;
        this.diplomaSeries = diplomaSeries;
        this.universityName = universityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getDiplomaNumber() {
        return diplomaNumber;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }

    public String getDiplomaSeries() {
        return diplomaSeries;
    }

    public void setDiplomaSeries(String diplomaSeries) {
        this.diplomaSeries = diplomaSeries;
    }

    public int getUniversityName() {
        return universityName;
    }

    public void setUniversityName(int universityName) {
        this.universityName = universityName;
    }
}
