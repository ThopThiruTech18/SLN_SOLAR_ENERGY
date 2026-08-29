package in.thirutech.institute.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(nullable = false, length = 100)
    private String courseName;

    private Integer durationInMonths;

    @Column(precision = 10, scale = 2)
    private BigDecimal feeAmount;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean status = true;

    @OneToMany(mappedBy = "course")
    private List<Batch> batches;

    @OneToMany(mappedBy = "course")
    private List<Payment> payments;

    @OneToMany(mappedBy = "course")
    private List<CourseMaterial> courseMaterials;

    public Course() {
    }

    public Course(Integer courseId, String courseName, Integer durationInMonths, BigDecimal feeAmount,
            String description, Boolean status, List<Batch> batches, List<Payment> payments,
            List<CourseMaterial> courseMaterials) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.durationInMonths = durationInMonths;
        this.feeAmount = feeAmount;
        this.description = description;
        this.status = status;
        this.batches = batches;
        this.payments = payments;
        this.courseMaterials = courseMaterials;
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

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<CourseMaterial> getCourseMaterials() {
        return courseMaterials;
    }

    public void setCourseMaterials(List<CourseMaterial> courseMaterials) {
        this.courseMaterials = courseMaterials;
    }
}
