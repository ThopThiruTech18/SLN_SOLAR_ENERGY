package in.thirutech.institute.entity;

import in.thirutech.institute.enums.BatchStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "batches")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchId;

    private String batchName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private LocalDate startDate;

    private LocalDate endDate;

    private String schedule;

    @Enumerated(EnumType.STRING)
    private BatchStatus status = BatchStatus.UPCOMING;

    @OneToMany(mappedBy = "batch")
    private List<StudentBatch> studentBatches;

    @OneToMany(mappedBy = "batch")
    private List<Attendance> attendances;

    public Batch() {
    }

    public Batch(Integer batchId, String batchName, Course course, Trainer trainer, LocalDate startDate,
            LocalDate endDate, String schedule, BatchStatus status, List<StudentBatch> studentBatches,
            List<Attendance> attendances) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.course = course;
        this.trainer = trainer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedule = schedule;
        this.status = status;
        this.studentBatches = studentBatches;
        this.attendances = attendances;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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

    public List<StudentBatch> getStudentBatches() {
        return studentBatches;
    }

    public void setStudentBatches(List<StudentBatch> studentBatches) {
        this.studentBatches = studentBatches;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
