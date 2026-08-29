package in.thirutech.institute.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TrainerResponse {
    private Integer trainerId;
    private Integer userId;
    private String fullName;
    private String email;
    private String phone;
    private String expertise;
    private Boolean status;
    private BigDecimal salary;
    private LocalDate joiningDate;

    public TrainerResponse() {
    }

    public TrainerResponse(Integer trainerId, String fullName, String email, String expertise, BigDecimal salary,
            LocalDate joiningDate) {
        this.trainerId = trainerId;
        this.fullName = fullName;
        this.email = email;
        this.expertise = expertise;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
