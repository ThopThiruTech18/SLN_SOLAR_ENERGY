package in.thirutech.institute.mapper;

import in.thirutech.institute.dto.response.CourseResponse;
import in.thirutech.institute.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponse toResponse(Course course) {
        if (course == null) {
            return null;
        }
        CourseResponse response = new CourseResponse();
        response.setCourseId(course.getCourseId());
        response.setCourseName(course.getCourseName());
        response.setDurationInMonths(course.getDurationInMonths());
        response.setFeeAmount(course.getFeeAmount());
        response.setDescription(course.getDescription());
        response.setStatus(course.getStatus());
        return response;
    }
}
