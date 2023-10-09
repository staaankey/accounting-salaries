package staaankey.group.accountingsalaries.security.educations.web.dto;

public class EducationDto {
    private String degree;
    private int diplomaNumber;
    private String diplomaSeries;
    private int universityName;

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
