package in.thirutech.institute.dto.request;

import java.math.BigDecimal;

public class CourseRequest {
    private String courseName;
    private Integer durationInMonths;
    private BigDecimal feeAmount;
    private String description;
    private Boolean status;

    public CourseRequest() {
    }

    public CourseRequest(String courseName, Integer durationInMonths, BigDecimal feeAmount, String description,
            Boolean status) {
        this.courseName = courseName;
        this.durationInMonths = durationInMonths;
        this.feeAmount = feeAmount;
        this.description = description;
        this.status = status;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(Integer durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
