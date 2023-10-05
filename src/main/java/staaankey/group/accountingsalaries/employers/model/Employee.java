package staaankey.group.accountingsalaries.employers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "employers")
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

    public Employee() {

    }

    public Employee(int employeeId, String fullName, String photoUrl, String phone, String address, int passportId, int workplaceId, int titlesId, int educationId, int departmentId) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.photoUrl = photoUrl;
        this.phone = phone;
        this.address = address;
        this.passportId = passportId;
        this.workplaceId = workplaceId;
        this.titlesId = titlesId;
        this.educationId = educationId;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public int getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    public int getTitlesId() {
        return titlesId;
    }

    public void setTitlesId(int titlesId) {
        this.titlesId = titlesId;
    }

    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
