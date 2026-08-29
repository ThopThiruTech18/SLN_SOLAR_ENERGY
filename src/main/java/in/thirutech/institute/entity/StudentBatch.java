package in.thirutech.institute.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_batch")
public class StudentBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private LocalDate enrollmentDate;

    public StudentBatch() {
    }

    public StudentBatch(Integer id, Student student, Batch batch, LocalDate enrollmentDate) {
        this.id = id;
        this.student = student;
        this.batch = batch;
        this.enrollmentDate = enrollmentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
