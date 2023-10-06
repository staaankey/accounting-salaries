package staaankey.group.accountingsalaries.passports.web.dto;

import java.time.LocalDate;

public class PassportDto {
    private String series;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiring;
    private String placeOfIssue;

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
