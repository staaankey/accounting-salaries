package staaankey.group.accountingsalaries.timesheets.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timesheets")
public class Timesheet {
    @Id
    @Column(name = "timesheet_id", nullable = false)
    private Integer id;
}
