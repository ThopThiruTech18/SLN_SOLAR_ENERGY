package in.thirutech.institute.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String expertise;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    private LocalDate joiningDate;

    @OneToMany(mappedBy = "trainer")
    private List<Batch> batches;

    public Trainer() {
    }

    public Trainer(Integer trainerId, User user, String expertise, BigDecimal salary, LocalDate joiningDate,
            List<Batch> batches) {
        this.trainerId = trainerId;
        this.user = user;
        this.expertise = expertise;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.batches = batches;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
