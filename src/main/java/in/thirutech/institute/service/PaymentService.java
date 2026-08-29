package in.thirutech.institute.service;

import in.thirutech.institute.dto.request.PaymentRequest;
import in.thirutech.institute.dto.response.PaymentResponse;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);

    PaymentResponse updatePayment(Integer id, PaymentRequest request);

    PaymentResponse getPaymentById(Integer id);

    List<PaymentResponse> getAllPayments();

    List<PaymentResponse> getPaymentsByStudentId(Integer studentId);

    void deletePayment(Integer id);

    BigDecimal getTotalRevenue();
}
