package in.thirutech.institute.repository;

import in.thirutech.institute.entity.Student;
import in.thirutech.institute.enums.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByStatus(StudentStatus status);

    List<Student> findByUserFullNameContaining(String name);

    Optional<Student> findByUserUserId(Integer userId);

    Optional<Student> findByUserEmail(String email);
}
