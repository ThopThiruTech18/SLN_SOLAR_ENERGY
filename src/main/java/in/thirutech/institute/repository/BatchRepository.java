package in.thirutech.institute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thirutech.institute.entity.Batch;
import in.thirutech.institute.enums.BatchStatus;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
    List<Batch> findByStatus(BatchStatus status);

    List<Batch> findByTrainerTrainerId(Integer trainerId);

    List<Batch> findByCourseCourseId(Integer courseId);
}
