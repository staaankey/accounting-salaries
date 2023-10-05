package staaankey.group.accountingsalaries.passports.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "passports")
public class Passport {
    @Id
    @Column(name = "passport_id", nullable = false)
    private Integer id;
    @Column(name = "series")
    private String series;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;
    @Column(name = "date_of_expiring")
    private LocalDate dateOfExpiring;
    @Column(name = "place_of_issue")
    private String placeOfIssue;

    public Passport() {
    }

    public Passport(
            Integer id, String series, LocalDate dateOfBirth, String placeOfBirth,
            LocalDate dateOfIssue, LocalDate dateOfExpiring, String placeOfIssue
    ) {
        this.id = id;
        this.series = series;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiring = dateOfExpiring;
        this.placeOfIssue = placeOfIssue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfExpiring() {
        return dateOfExpiring;
    }

    public void setDateOfExpiring(LocalDate dateOfExpiring) {
        this.dateOfExpiring = dateOfExpiring;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }
}
