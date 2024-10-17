package com.thentrees.orderservice.handler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Map<String, String> errors;
}
