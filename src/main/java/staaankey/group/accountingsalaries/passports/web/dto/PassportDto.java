package staaankey.group.accountingsalaries.passports.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PassportDto {
    private String series;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiring;
    private String placeOfIssue;
}
