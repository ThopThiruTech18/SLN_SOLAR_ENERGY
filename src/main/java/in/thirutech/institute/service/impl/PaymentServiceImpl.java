package in.thirutech.institute.service.impl;

import in.thirutech.institute.dto.request.PaymentRequest;
import in.thirutech.institute.dto.response.PaymentResponse;
import in.thirutech.institute.entity.Course;
import in.thirutech.institute.entity.Payment;
import in.thirutech.institute.entity.Student;
import in.thirutech.institute.enums.PaymentStatus;
import in.thirutech.institute.exception.ResourceNotFoundException;
import in.thirutech.institute.mapper.PaymentMapper;
import in.thirutech.institute.repository.CourseRepository;
import in.thirutech.institute.repository.PaymentRepository;
import in.thirutech.institute.repository.StudentRepository;
import in.thirutech.institute.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, StudentRepository studentRepository,
            CourseRepository courseRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setCourse(course);
        payment.setAmountPaid(request.getAmountPaid());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getStatus() != null ? request.getStatus() : PaymentStatus.PAID);

        payment = paymentRepository.save(payment);
        return paymentMapper.toResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return paymentMapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    // @Override
    // public List<PaymentResponse> getPaymentsByStudent(Integer studentId) {
    // return paymentRepository.findByStudentStudentId(studentId).stream()
    // .map(paymentMapper::toResponse)
    // .collect(Collectors.toList());
    // }

    @Override
    public BigDecimal getTotalRevenue() {
        BigDecimal revenue = paymentRepository.getTotalRevenue();
        return revenue != null ? revenue : BigDecimal.ZERO;
    }

    @Override
    public PaymentResponse updatePayment(Integer id, PaymentRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PaymentResponse> getPaymentsByStudentId(Integer studentId) {
        return paymentRepository.findByStudentStudentId(studentId).stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Integer id) {
        // TODO Auto-generated method stub
    }
}
