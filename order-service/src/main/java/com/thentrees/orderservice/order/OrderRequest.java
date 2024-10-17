package com.thentrees.orderservice.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thentrees.orderservice.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderRequest {
    private Integer id;
    private String reference;
    @Positive(message = "order amount must be greater than 0")
    private BigDecimal amount;
    @NotNull(message = "payment method should be precised")
    private PaymentMethod paymentMethod;
    @NotNull(message = "customer should be present")
    @NotEmpty(message = "customer should be present")
    @NotBlank(message = "customer should be present")
    private String customerId;
    @NotEmpty(message = "you should at least purchase one product")
    private List<PurchaseRequest> products;

}
