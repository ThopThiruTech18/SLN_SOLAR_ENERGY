package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.CourseRequest;
import in.thirutech.institute.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseRequest request);

    CourseResponse updateCourse(Integer id, CourseRequest request);

    CourseResponse getCourseById(Integer id);

    List<CourseResponse> getAllCourses();

    List<CourseResponse> getActiveCourses();

    void deleteCourse(Integer id);
}
