package in.thirutech.institute.repository;

import in.thirutech.institute.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByStudentStudentId(Integer studentId);

    List<Attendance> findByBatchBatchId(Integer batchId);

    List<Attendance> findByBatchBatchIdAndAttendanceDate(Integer batchId, LocalDate date);
}
