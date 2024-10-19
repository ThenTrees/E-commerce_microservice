package com.thentrees.notification.kafka.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentConfirmation {
    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

}
