package com.thentrees.orderservice.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  PurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Integer productId;
    @Positive(message = "Quantity is mandatory")
    private double quantity;
}
