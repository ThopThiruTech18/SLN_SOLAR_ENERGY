package in.thirutech.institute.dto.response;

import in.thirutech.institute.enums.PaymentMode;
import in.thirutech.institute.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentResponse {
    private Integer paymentId;
    private String studentName;
    private String courseName;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private PaymentMode paymentMode;
    private PaymentStatus status;

    public PaymentResponse() {
    }

    public PaymentResponse(Integer paymentId, String studentName, String courseName, BigDecimal amountPaid,
            LocalDate paymentDate, PaymentMode paymentMode, PaymentStatus status) {
        this.paymentId = paymentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMode = paymentMode;
        this.status = status;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
