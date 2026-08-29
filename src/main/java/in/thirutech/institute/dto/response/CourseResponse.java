package in.thirutech.institute.dto.response;

import java.math.BigDecimal;

public class CourseResponse {
    private Integer courseId;
    private String courseName;
    private Integer durationInMonths;
    private BigDecimal feeAmount;
    private String description;
    private Boolean status;

    public CourseResponse() {
    }

    public CourseResponse(Integer courseId, String courseName, Integer durationInMonths, BigDecimal feeAmount,
            String description, Boolean status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.durationInMonths = durationInMonths;
        this.feeAmount = feeAmount;
        this.description = description;
        this.status = status;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
