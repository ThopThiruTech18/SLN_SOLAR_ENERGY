package in.thirutech.institute.service.impl;

import in.thirutech.institute.dto.request.StudentRequest;
import in.thirutech.institute.dto.response.StudentResponse;
import in.thirutech.institute.entity.Student;
import in.thirutech.institute.entity.User;
import in.thirutech.institute.enums.StudentStatus;
import in.thirutech.institute.exception.ResourceNotFoundException;
import in.thirutech.institute.mapper.StudentMapper;
import in.thirutech.institute.repository.AttendanceRepository;
import in.thirutech.institute.repository.PaymentRepository;
import in.thirutech.institute.repository.StudentBatchRepository;
import in.thirutech.institute.repository.StudentRepository;
import in.thirutech.institute.repository.UserRepository;
import in.thirutech.institute.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final StudentBatchRepository studentBatchRepository;
    private final AttendanceRepository attendanceRepository;
    private final PaymentRepository paymentRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository,
            StudentMapper studentMapper, StudentBatchRepository studentBatchRepository,
            AttendanceRepository attendanceRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.studentMapper = studentMapper;
        this.studentBatchRepository = studentBatchRepository;
        this.attendanceRepository = attendanceRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Student student = new Student();
        student.setUser(user);
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setQualification(request.getQualification());
        student.setJoiningDate(request.getJoiningDate());
        student.setStatus(request.getStatus() != null ? request.getStatus() : StudentStatus.ACTIVE);

        student = studentRepository.save(student);
        return studentMapper.toResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Integer id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setQualification(request.getQualification());
        student.setJoiningDate(request.getJoiningDate());
        if (request.getStatus() != null) {
            student.setStatus(request.getStatus());
        }

        student = studentRepository.save(student);
        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse getStudentById(Integer id) {
        if (id == null) {
            throw new ResourceNotFoundException("Student id cannot be null");
        }
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return studentMapper.toResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> getActiveStudents() {
        return studentRepository.findByStatus(StudentStatus.ACTIVE).stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // 1. Delete attendance records
        attendanceRepository.deleteAll(attendanceRepository.findByStudentStudentId(id));

        // 2. Delete enrollment (student_batch) records
        studentBatchRepository.deleteAll(studentBatchRepository.findByStudentStudentId(id));

        // 3. Delete payment records
        paymentRepository.deleteAll(paymentRepository.findByStudentStudentId(id));

        // 4. Delete the student profile
        studentRepository.deleteById(id);

        // 5. Delete the linked user account
        if (student.getUser() != null) {
            userRepository.deleteById(student.getUser().getUserId());
        }
    }
}
