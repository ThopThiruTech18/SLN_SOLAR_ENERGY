package in.thirutech.institute.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TrainerRequest {
    private Integer userId;
    private String expertise;
    private BigDecimal salary;
    private LocalDate joiningDate;

    public TrainerRequest() {
    }

    public TrainerRequest(Integer userId, String expertise, BigDecimal salary, LocalDate joiningDate) {
        this.userId = userId;
        this.expertise = expertise;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
