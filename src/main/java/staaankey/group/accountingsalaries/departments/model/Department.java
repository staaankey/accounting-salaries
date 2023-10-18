package staaankey.group.accountingsalaries.departments.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    private Integer id;
    private Integer parentId;
    private String name;
}
