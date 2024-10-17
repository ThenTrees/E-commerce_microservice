package com.thentrees.orderservice.orderline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponse {
    private Integer id;
    private double quantity;
}
