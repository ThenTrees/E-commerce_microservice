package com.thentrees.orderservice.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponse implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
