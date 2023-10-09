package staaankey.group.accountingsalaries.titiles.web.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class TitleDto {
    private String titleName;
    private int speciality;
    private String serialNumber;
    private LocalDate dateOfIssue;
    private int nameOfPlace;

    public TitleDto() {
    }

    public TitleDto(String titleName, int speciality, String serialNumber, LocalDate dateOfIssue, int nameOfPlace) {
        this.titleName = titleName;
        this.speciality = speciality;
        this.serialNumber = serialNumber;
        this.dateOfIssue = dateOfIssue;
        this.nameOfPlace = nameOfPlace;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getSpeciality() {
        return speciality;
    }

    public void setSpeciality(int speciality) {
        this.speciality = speciality;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public int getNameOfPlace() {
        return nameOfPlace;
    }

    public void setNameOfPlace(int nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }
}
