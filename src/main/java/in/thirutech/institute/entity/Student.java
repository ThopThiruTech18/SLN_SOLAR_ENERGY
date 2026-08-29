package in.thirutech.institute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.thirutech.institute.enums.StudentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String qualification;

    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    private StudentStatus status = StudentStatus.ACTIVE;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentBatch> studentBatches;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Attendance> attendances;

    public Student() {
    }

    public Student(Integer studentId, User user, String phone, String address, String qualification,
            LocalDate joiningDate, StudentStatus status, List<StudentBatch> studentBatches, List<Payment> payments,
            List<Attendance> attendances) {
        this.studentId = studentId;
        this.user = user;
        this.phone = phone;
        this.address = address;
        this.qualification = qualification;
        this.joiningDate = joiningDate;
        this.status = status;
        this.studentBatches = studentBatches;
        this.payments = payments;
        this.attendances = attendances;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<StudentBatch> getStudentBatches() {
        return studentBatches;
    }

    public void setStudentBatches(List<StudentBatch> studentBatches) {
        this.studentBatches = studentBatches;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
