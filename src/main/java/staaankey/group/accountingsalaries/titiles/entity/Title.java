package staaankey.group.accountingsalaries.titiles.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "titles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id", nullable = false)
    private long id;
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
