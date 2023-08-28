package staaankey.group.accountingsalaries.departments.web.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class DepartmentDto {

    private Integer parentId;
    private String name;

    public DepartmentDto(Integer parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentDto() {
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
