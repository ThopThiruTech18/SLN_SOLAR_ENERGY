package in.thirutech.institute.repository;

import in.thirutech.institute.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByStudentStudentId(Integer studentId);

    @Query("SELECT SUM(p.amountPaid) FROM Payment p")
    public BigDecimal getTotalRevenue();
}
