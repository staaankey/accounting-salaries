package staaankey.group.accountingsalaries.tabels.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "reports")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    private int workedDays;
    private int hoursAtTheDay;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return id == report.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
