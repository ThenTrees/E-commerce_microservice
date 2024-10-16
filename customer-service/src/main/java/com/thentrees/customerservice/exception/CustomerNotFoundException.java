package com.thentrees.customerservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerNotFoundException extends RuntimeException {
    private final String message;
}
