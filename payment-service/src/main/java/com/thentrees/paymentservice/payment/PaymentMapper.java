package com.thentrees.paymentservice.payment;

import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }
        return Payment.builder()
                .id(request.getId())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .orderId(request.getOrderId())
                .build();
    }
}
