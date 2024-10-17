package com.thentrees.customerservice.service;

import com.thentrees.customerservice.dto.request.CustomerRequest;
import com.thentrees.customerservice.dto.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {
    String createCustomer(CustomerRequest request);
    void updateCustomer(CustomerRequest request);
    void deleteCustomer(String id);
    List<CustomerResponse> findAllCustomer();
    CustomerResponse findCustomerById(String id);
    boolean isCustomerExist(String id);

}
