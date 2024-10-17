package com.thentrees.orderservice.kafka;
import com.thentrees.orderservice.customer.CustomerResponse;
import com.thentrees.orderservice.order.PaymentMethod;
import com.thentrees.orderservice.product.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation implements Serializable {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customerResponse;
    private List<PurchaseResponse> products;

}
