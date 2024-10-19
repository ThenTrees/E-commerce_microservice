package com.thentrees.notification.kafka.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
