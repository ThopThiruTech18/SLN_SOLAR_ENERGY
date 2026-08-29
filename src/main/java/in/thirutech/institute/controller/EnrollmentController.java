package in.thirutech.institute.controller;

import in.thirutech.institute.dto.request.EnrollmentRequest;
import in.thirutech.institute.entity.*;
import in.thirutech.institute.enums.StudentStatus;
import in.thirutech.institute.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;
    private final StudentBatchRepository studentBatchRepository;
    private final UserRepository userRepository;

    public EnrollmentController(StudentRepository studentRepository,
            BatchRepository batchRepository,
            StudentBatchRepository studentBatchRepository,
            UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.batchRepository = batchRepository;
        this.studentBatchRepository = studentBatchRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody EnrollmentRequest request) {

        // Step 1: Try to find student by studentId
        Student student = null;
        if (request.getStudentId() != null && request.getStudentId() > 0) {
            student = studentRepository.findById(request.getStudentId()).orElse(null);
        }

        // Step 2: Fallback — try to find student by email (handles legacy users without
        // student record)
        if (student == null && request.getEmail() != null && !request.getEmail().isBlank()) {
            student = studentRepository.findByUserEmail(request.getEmail()).orElse(null);

            // Step 3: No student record exists — auto-create one linked to the user account
            if (student == null) {
                User user = userRepository.findByEmail(request.getEmail()).orElse(null);
                if (user != null) {
                    Student newStudent = new Student();
                    newStudent.setUser(user);
                    newStudent.setStatus(StudentStatus.ACTIVE);
                    student = studentRepository.save(newStudent);
                }
            }
        }

        if (student == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Student not found. Please ensure your account has a student profile.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

        // Find batch
        Batch batch = batchRepository.findById(request.getBatchId()).orElse(null);
        if (batch == null) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "Batch not found with id: " + request.getBatchId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        }

        // Check if already enrolled
        List<StudentBatch> existing = studentBatchRepository.findByStudentStudentId(student.getStudentId());
        boolean alreadyEnrolled = existing.stream()
                .anyMatch(sb -> sb.getBatch().getBatchId().equals(batch.getBatchId()));
        if (alreadyEnrolled) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "You are already enrolled in this batch.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
        }

        // Create enrollment
        StudentBatch enrollment = new StudentBatch();
        enrollment.setStudent(student);
        enrollment.setBatch(batch);
        enrollment.setEnrollmentDate(request.getEnrollmentDate());
        studentBatchRepository.save(enrollment);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Enrollment successful!");
        response.put("studentId", student.getStudentId());
        response.put("batchId", batch.getBatchId());
        response.put("batchName", batch.getBatchName());
        response.put("enrollmentDate", request.getEnrollmentDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getEnrollmentsByStudent(@PathVariable Integer studentId) {
        List<StudentBatch> enrollments = studentBatchRepository.findByStudentStudentId(studentId);

        List<Map<String, Object>> result = enrollments.stream().map(sb -> {
            Map<String, Object> map = new java.util.LinkedHashMap<>();
            map.put("enrollmentId", sb.getId());
            map.put("enrollmentDate", sb.getEnrollmentDate() != null ? sb.getEnrollmentDate().toString() : null);

            if (sb.getBatch() != null) {
                in.thirutech.institute.entity.Batch b = sb.getBatch();
                map.put("batchId", b.getBatchId());
                map.put("batchName", b.getBatchName());
                map.put("startDate", b.getStartDate() != null ? b.getStartDate().toString() : null);
                map.put("endDate", b.getEndDate() != null ? b.getEndDate().toString() : null);
                map.put("schedule", b.getSchedule());
                map.put("status", b.getStatus() != null ? b.getStatus().name() : null);
                map.put("courseName", b.getCourse() != null ? b.getCourse().getCourseName() : null);
                map.put("trainerName", b.getTrainer() != null && b.getTrainer().getUser() != null
                        ? b.getTrainer().getUser().getFullName()
                        : null);
            }
            return map;
        }).collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
