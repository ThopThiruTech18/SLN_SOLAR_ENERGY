package in.thirutech.institute.controller;

import in.thirutech.institute.entity.Attendance;
import in.thirutech.institute.entity.Batch;
import in.thirutech.institute.entity.Student;
import in.thirutech.institute.repository.AttendanceRepository;
import in.thirutech.institute.repository.BatchRepository;
import in.thirutech.institute.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;

    public AttendanceController(AttendanceRepository attendanceRepository,
            StudentRepository studentRepository,
            BatchRepository batchRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.batchRepository = batchRepository;
    }

    // ── Get all attendance records ─────────────────────────────────
    @GetMapping
    public ResponseEntity<?> getAllAttendance() {
        List<Attendance> list = attendanceRepository.findAll();
        List<Map<String, Object>> result = list.stream().map(this::toMap).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // ── Get attendance by student ──────────────────────────────────
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getByStudent(@PathVariable Integer studentId) {
        List<Attendance> list = attendanceRepository.findByStudentStudentId(studentId);
        List<Map<String, Object>> result = list.stream().map(this::toMap).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // ── Mark attendance ────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<?> markAttendance(@RequestBody Map<String, Object> request) {
        try {
            Integer studentId = (Integer) request.get("studentId");
            Integer batchId = (Integer) request.get("batchId");
            String dateStr = (String) request.get("attendanceDate");
            String statusStr = (String) request.get("status");

            if (studentId == null || batchId == null || dateStr == null || statusStr == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "studentId, batchId, attendanceDate and status are required"));
            }

            Student student = studentRepository.findById(studentId).orElse(null);
            if (student == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Student not found with id: " + studentId));
            }

            Batch batch = batchRepository.findById(batchId).orElse(null);
            if (batch == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Batch not found with id: " + batchId));
            }

            Attendance.AttendanceStatus status;
            try {
                status = Attendance.AttendanceStatus.valueOf(statusStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                status = Attendance.AttendanceStatus.PRESENT;
            }

            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setBatch(batch);
            attendance.setAttendanceDate(LocalDate.parse(dateStr));
            attendance.setStatus(status);

            Attendance saved = attendanceRepository.save(attendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(toMap(saved));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to mark attendance: " + e.getMessage()));
        }
    }

    // ── Delete attendance record ───────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Integer id) {
        if (!attendanceRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Attendance record not found"));
        }
        attendanceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ── Helper: convert Attendance entity to flat map ─────────────
    private Map<String, Object> toMap(Attendance a) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("attendanceId", a.getAttendanceId());
        map.put("studentName", a.getStudent() != null
                ? (a.getStudent().getUser() != null ? a.getStudent().getUser().getFullName() : "Unknown")
                : "Unknown");
        map.put("batchName", a.getBatch() != null ? a.getBatch().getBatchName() : "Unknown");
        map.put("studentId", a.getStudent() != null ? a.getStudent().getStudentId() : null);
        map.put("batchId", a.getBatch() != null ? a.getBatch().getBatchId() : null);
        map.put("attendanceDate", a.getAttendanceDate() != null ? a.getAttendanceDate().toString() : null);
        map.put("status", a.getStatus() != null ? a.getStatus().name() : null);
        return map;
    }
}
