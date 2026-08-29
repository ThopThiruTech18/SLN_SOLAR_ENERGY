package in.thirutech.institute.dto.request;

import in.thirutech.institute.enums.PaymentMode;
import in.thirutech.institute.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequest {
    private Integer studentId;
    private Integer courseId;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private PaymentMode paymentMode;
    private PaymentStatus status;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer studentId, Integer courseId, BigDecimal amountPaid, LocalDate paymentDate,
            PaymentMode paymentMode, PaymentStatus status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMode = paymentMode;
        this.status = status;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
