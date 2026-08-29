package in.thirutech.institute.repository;

import in.thirutech.institute.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	List<Trainer> findByUser_StatusTrue();
}
