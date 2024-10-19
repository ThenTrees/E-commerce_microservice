package com.thentrees.orderservice.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse implements Serializable {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;

}
