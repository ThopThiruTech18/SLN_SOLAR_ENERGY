package in.thirutech.institute.dto.request;

import in.thirutech.institute.entity.Attendance.AttendanceStatus;

import java.time.LocalDate;

public class AttendanceRequest {
    private Integer studentId;
    private Integer batchId;
    private LocalDate attendanceDate;
    private AttendanceStatus status;

    public AttendanceRequest() {
    }

    public AttendanceRequest(Integer studentId, Integer batchId, LocalDate attendanceDate, AttendanceStatus status) {
        this.studentId = studentId;
        this.batchId = batchId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
