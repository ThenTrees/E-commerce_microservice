package com.thentrees.orderservice.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {
    private Integer id;
    private String reference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerId;
}
