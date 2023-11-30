package staaankey.group.accountingsalaries.employers.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EmployeeDto {
    private String fullName;
    private String photoUrl;
    private String phone;
    private String address;
    private int passportId;
    private int workplaceId;
    private int titlesId;
    private int educationId;
    private int departmentId;
}
