package in.thirutech.institute.repository;

import in.thirutech.institute.entity.StudentBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentBatchRepository extends JpaRepository<StudentBatch, Integer> {
    List<StudentBatch> findByStudentStudentId(Integer studentId);
    List<StudentBatch> findByBatchBatchId(Integer batchId);
}
