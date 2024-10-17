package com.thentrees.orderservice.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseResponse implements Serializable {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;

}
