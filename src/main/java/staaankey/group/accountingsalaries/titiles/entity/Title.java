package staaankey.group.accountingsalaries.titiles.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "titles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
