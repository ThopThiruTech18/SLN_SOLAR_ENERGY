package in.thirutech.institute.service.impl;

import in.thirutech.institute.dto.request.CourseRequest;
import in.thirutech.institute.dto.response.CourseResponse;
import in.thirutech.institute.entity.Batch;
import in.thirutech.institute.entity.Course;
import in.thirutech.institute.exception.ResourceNotFoundException;
import in.thirutech.institute.mapper.CourseMapper;
import in.thirutech.institute.repository.AttendanceRepository;
import in.thirutech.institute.repository.BatchRepository;
import in.thirutech.institute.repository.CourseMaterialRepository;
import in.thirutech.institute.repository.CourseRepository;
import in.thirutech.institute.repository.StudentBatchRepository;
import in.thirutech.institute.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final BatchRepository batchRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentBatchRepository studentBatchRepository;
    private final CourseMaterialRepository courseMaterialRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper,
            BatchRepository batchRepository, AttendanceRepository attendanceRepository,
            StudentBatchRepository studentBatchRepository, CourseMaterialRepository courseMaterialRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.batchRepository = batchRepository;
        this.attendanceRepository = attendanceRepository;
        this.studentBatchRepository = studentBatchRepository;
        this.courseMaterialRepository = courseMaterialRepository;
    }

    @Override
    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDurationInMonths(request.getDurationInMonths());
        course.setFeeAmount(request.getFeeAmount());
        course.setDescription(request.getDescription());
        course.setStatus(request.getStatus() != null ? request.getStatus() : true);

        course = courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    @Override
    @Transactional
    public CourseResponse updateCourse(Integer id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setCourseName(request.getCourseName());
        course.setDurationInMonths(request.getDurationInMonths());
        course.setFeeAmount(request.getFeeAmount());
        course.setDescription(request.getDescription());
        if (request.getStatus() != null) {
            course.setStatus(request.getStatus());
        }

        course = courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    @Override
    public CourseResponse getCourseById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return courseMapper.toResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponse> getActiveCourses() {
        return courseRepository.findByStatusTrue().stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteCourse(Integer id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }

        // 1. Delete course materials (notes/files)
        courseMaterialRepository.deleteAll(courseMaterialRepository.findByCourseCourseId(id));

        // 2. For each batch linked to this course, delete children then the batch
        List<Batch> batches = batchRepository.findByCourseCourseId(id);
        for (Batch batch : batches) {
            Integer batchId = batch.getBatchId();
            // 2a. Delete attendance records for this batch
            attendanceRepository.deleteAll(attendanceRepository.findByBatchBatchId(batchId));
            // 2b. Delete enrollment (student_batch) records for this batch
            studentBatchRepository.deleteAll(studentBatchRepository.findByBatchBatchId(batchId));
        }

        // 3. Delete all batches for this course
        batchRepository.deleteAll(batches);

        // 4. Finally, delete the course itself
        courseRepository.deleteById(id);
    }
}
