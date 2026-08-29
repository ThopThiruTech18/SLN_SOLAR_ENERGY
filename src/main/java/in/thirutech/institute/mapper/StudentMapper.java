package in.thirutech.institute.mapper;

import org.springframework.stereotype.Component;

import in.thirutech.institute.dto.response.StudentResponse;
import in.thirutech.institute.entity.Student;

@Component
public class StudentMapper {

    public StudentResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }
        StudentResponse response = new StudentResponse();
        // Set the actual studentId from the students table (NOT userId)
        response.setStudentId(student.getStudentId());
        if (student.getUser() != null) {
            // Only map user fields — do NOT overwrite studentId with userId
            response.setFullName(student.getUser().getFullName());
            response.setEmail(student.getUser().getEmail());
        }
        response.setPhone(student.getPhone());
        response.setAddress(student.getAddress());
        response.setQualification(student.getQualification());
        response.setJoiningDate(student.getJoiningDate());
        response.setStatus(student.getStatus());
        return response;
    }
}
