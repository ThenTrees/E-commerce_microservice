package com.thentrees.orderservice.orderline;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private double quantity;
}
