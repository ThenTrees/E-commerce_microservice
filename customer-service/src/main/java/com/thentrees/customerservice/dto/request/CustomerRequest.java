package com.thentrees.customerservice.dto.request;

import com.thentrees.customerservice.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {
    private String id;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    private Address address;
}
