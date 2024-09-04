package com.example._20240904ordersysquardem00.service;

import com.example._20240904ordersysquardem00.model.Customer;
import com.example._20240904ordersysquardem00.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}