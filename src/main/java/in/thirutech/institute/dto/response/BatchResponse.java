package in.thirutech.institute.dto.response;

import in.thirutech.institute.enums.BatchStatus;

import java.time.LocalDate;

public class BatchResponse {
    private Integer batchId;
    private String batchName;
    private String courseName;
    private String trainerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String schedule;
    private BatchStatus status;
    private Integer enrolledStudents;

    public BatchResponse() {
    }

    public BatchResponse(Integer batchId, String batchName, String courseName, String trainerName, LocalDate startDate,
            LocalDate endDate, String schedule, BatchStatus status, Integer enrolledStudents) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.courseName = courseName;
        this.trainerName = trainerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedule = schedule;
        this.status = status;
        this.enrolledStudents = enrolledStudents;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public BatchStatus getStatus() {
        return status;
    }

    public void setStatus(BatchStatus status) {
        this.status = status;
    }

    public Integer getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Integer enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
