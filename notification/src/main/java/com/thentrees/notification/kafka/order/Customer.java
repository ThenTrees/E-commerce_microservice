package com.thentrees.notification.kafka.order;

import lombok.Data;

@Data
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
