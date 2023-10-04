package staaankey.group.accountingsalaries.employers.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class EmployeeDto {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

}
