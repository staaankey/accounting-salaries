package staaankey.group.accountingsalaries.titiles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "titles")
public class Title {
    @Id
    @Column(name = "title_id", nullable = false)
    private int id;
    @Column(name = "title_name")
    private String titleName;
    @Column(name = "speciality")
    private int speciality;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;
    @Column(name = "name_of_place")
    private int nameOfPlace;


    public Title() {
    }

    public Title(int id, String titleName, int speciality, String serialNumber, LocalDate dateOfIssue, int nameOfPlace) {
        this.id = id;
        this.titleName = titleName;
        this.speciality = speciality;
        this.serialNumber = serialNumber;
        this.dateOfIssue = dateOfIssue;
        this.nameOfPlace = nameOfPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
