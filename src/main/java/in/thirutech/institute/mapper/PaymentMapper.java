package in.thirutech.institute.mapper;

import in.thirutech.institute.dto.response.PaymentResponse;
import in.thirutech.institute.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {
        if (payment == null) {
            return null;
        }
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getPaymentId());
        if (payment.getStudent() != null && payment.getStudent().getUser() != null) {
            response.setStudentName(payment.getStudent().getUser().getFullName());
        }
        if (payment.getCourse() != null) {
            response.setCourseName(payment.getCourse().getCourseName());
        }
        response.setAmountPaid(payment.getAmountPaid());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentMode(payment.getPaymentMode());
        response.setStatus(payment.getStatus());
        return response;
    }
}
