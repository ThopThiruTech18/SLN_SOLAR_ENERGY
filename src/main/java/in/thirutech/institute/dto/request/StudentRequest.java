package in.thirutech.institute.dto.request;

import in.thirutech.institute.enums.StudentStatus;
import java.time.LocalDate;

public class StudentRequest {
    private Integer userId;
    private String phone;
    private String address;
    private String qualification;
    private LocalDate joiningDate;
    private StudentStatus status;

    public StudentRequest() {
    }

    public StudentRequest(Integer userId, String phone, String address, String qualification, LocalDate joiningDate,
            StudentStatus status) {
        this.userId = userId;
        this.phone = phone;
        this.address = address;
        this.qualification = qualification;
        this.joiningDate = joiningDate;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }
}
