package in.thirutech.institute.dto.response;

import in.thirutech.institute.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {
    private Integer studentId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String qualification;
    private LocalDate joiningDate;
    private StudentStatus status;

    public StudentResponse() {
    }

    public StudentResponse(Integer studentId, String fullName, String email, String phone, String address,
            String qualification, LocalDate joiningDate, StudentStatus status) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.qualification = qualification;
        this.joiningDate = joiningDate;
        this.status = status;
    }

    
}
