package in.thirutech.institute.dto.request;

import in.thirutech.institute.enums.BatchStatus;

import java.time.LocalDate;

public class BatchRequest {
    private String batchName;
    private Integer courseId;
    private Integer trainerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String schedule;
    private BatchStatus status;

    public BatchRequest() {
    }

    public BatchRequest(String batchName, Integer courseId, Integer trainerId, LocalDate startDate, LocalDate endDate,
            String schedule, BatchStatus status) {
        this.batchName = batchName;
        this.courseId = courseId;
        this.trainerId = trainerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedule = schedule;
        this.status = status;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
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
}
