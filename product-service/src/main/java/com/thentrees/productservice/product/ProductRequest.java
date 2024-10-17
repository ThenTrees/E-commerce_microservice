package com.thentrees.productservice.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Integer id;
    @NotNull(message = "Product name is required")
    private String name;
    @NotNull(message = "Product description is required")
    private String description;
    @Positive(message = "Available quantity should be positive")
    private double availableQuantity;
    @Positive(message = "Price should be positive")
    private BigDecimal price;
    @NotNull(message = "Product category is required")
    private Integer categoryId;
}
