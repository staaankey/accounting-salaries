package staaankey.group.accountingsalaries.employers.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false)
    private int employeeId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "passport_id")
    private int passportId;
    @Column(name = "workplace_id")
    private int workplaceId;
    @Column(name = "titles_id")
    private int titlesId;
    @Column(name = "education_id")
    private int educationId;
    @Column(name = "department_id")
    private int departmentId;

}
