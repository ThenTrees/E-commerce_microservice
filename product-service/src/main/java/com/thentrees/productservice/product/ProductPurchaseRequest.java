package com.thentrees.productservice.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductPurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Integer productId;
    @Positive(message = "Quantity is mandatory") // gia tri phai la so > 0
    private double quantity;
}
