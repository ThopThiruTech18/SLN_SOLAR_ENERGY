package in.thirutech.institute.service.impl;

import in.thirutech.institute.dto.response.DashboardResponse;
import in.thirutech.institute.enums.BatchStatus;
import in.thirutech.institute.enums.StudentStatus;
import in.thirutech.institute.repository.*;
import in.thirutech.institute.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final BatchRepository batchRepository;
    private final TrainerRepository trainerRepository;
    private final PaymentRepository paymentRepository;

    public DashboardServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository,
            BatchRepository batchRepository, TrainerRepository trainerRepository,
            PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.batchRepository = batchRepository;
        this.trainerRepository = trainerRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public DashboardResponse getDashboardStats() {
        DashboardResponse response = new DashboardResponse();

        response.setTotalStudents(studentRepository.count());
        response.setActiveStudents((long) studentRepository.findByStatus(StudentStatus.ACTIVE).size());
        response.setTotalCourses(courseRepository.count());
        response.setTotalBatches(batchRepository.count());
        response.setOngoingBatches((long) batchRepository.findByStatus(BatchStatus.ONGOING).size());
        response.setTotalTrainers(trainerRepository.count());
        response.setTotalRevenue(paymentRepository.getTotalRevenue());
        response.setTotalPayments(paymentRepository.count());

        return response;
    }
}
