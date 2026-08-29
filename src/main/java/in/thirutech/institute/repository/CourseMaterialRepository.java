package in.thirutech.institute.repository;

import in.thirutech.institute.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Integer> {
    List<CourseMaterial> findByCourseCourseId(Integer courseId);
}
