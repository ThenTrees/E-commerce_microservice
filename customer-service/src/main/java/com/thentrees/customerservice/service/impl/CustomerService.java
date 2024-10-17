package com.thentrees.customerservice.service.impl;

import com.thentrees.customerservice.dto.request.CustomerRequest;
import com.thentrees.customerservice.dto.response.CustomerResponse;
import com.thentrees.customerservice.exception.CustomerNotFoundException;
import com.thentrees.customerservice.mapper.CustomerMapper;
import com.thentrees.customerservice.model.Customer;
import com.thentrees.customerservice.repository.CustomerRepository;
import com.thentrees.customerservice.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + request.getId()));
        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }
    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.getFirstName())) {
            customer.setFirstName(request.getFirstName());
        }
        if(StringUtils.isNotBlank(request.getLastName())) {
            customer.setLastName(request.getLastName());
        }
        if(request.getAddress() != null){
          customer.setAddress(request.getAddress());
        }
    }

    @Override
    public void deleteCustomer(String id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerResponse> findAllCustomer() {
        return this.customerRepository.findAll()
                .stream()
                .map(this.customerMapper::fromCustomer)
                .toList();
    }

    @Override
    public CustomerResponse findCustomerById(String id) {
        return this.customerRepository.findById(id)
                .map(this.customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public boolean isCustomerExist(String id) {
        return this.customerRepository.findById(id).isPresent();
    }
}
