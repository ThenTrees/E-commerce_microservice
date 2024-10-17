package com.thentrees.notification.kafka.order;

import com.thentrees.notification.kafka.payment.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private Customer customer;
    private List<Product> products;
}
