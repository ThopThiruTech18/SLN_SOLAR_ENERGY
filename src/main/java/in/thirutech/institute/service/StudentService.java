package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.StudentRequest;
import in.thirutech.institute.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest request);

    StudentResponse updateStudent(Integer id, StudentRequest request);

    StudentResponse getStudentById(Integer id);

    List<StudentResponse> getAllStudents();

    List<StudentResponse> getActiveStudents();

    void deleteStudent(Integer id);
}
