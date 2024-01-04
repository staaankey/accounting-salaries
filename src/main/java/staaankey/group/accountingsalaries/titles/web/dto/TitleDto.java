package staaankey.group.accountingsalaries.titles.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitleDto {
    private String titleName;
    private int speciality;
    private String serialNumber;
    private LocalDate dateOfIssue;
    private int nameOfPlace;
}
