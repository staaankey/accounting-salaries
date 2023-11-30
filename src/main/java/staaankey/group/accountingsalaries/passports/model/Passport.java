package staaankey.group.accountingsalaries.passports.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "passports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id", nullable = false)
    private Long id;
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
}
