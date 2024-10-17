package com.thentrees.customerservice.dto.response;

import com.thentrees.customerservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
