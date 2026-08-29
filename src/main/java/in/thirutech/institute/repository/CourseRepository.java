package in.thirutech.institute.repository;

import in.thirutech.institute.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByStatusTrue();
    List<Course> findByCourseNameContaining(String name);
}
