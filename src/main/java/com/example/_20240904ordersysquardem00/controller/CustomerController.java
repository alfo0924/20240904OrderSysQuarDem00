package com.example._20240904ordersysquardem00.controller;

import com.example._20240904ordersysquardem00.model.Customer;
import com.example._20240904ordersysquardem00.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/{id}")
    public String viewCustomer(@PathVariable Long id, Model model) {
        customerService.getCustomerById(id).ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer-details";
    }

    @GetMapping("/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        customerService.getCustomerById(id).ifPresent(customer -> model.addAttribute("customer", customer));
        return "customer-form";
    }

    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customer.setCustomerId(id);
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }



    // API - get all customers
    @GetMapping("/api")
    @ResponseBody
    public List<Customer> getCustomersApi() {
        return customerService.getAllCustomers();
    }

    // API - get a single data of customer
    @GetMapping("/api/{id}")
    @ResponseBody
    public Customer getCustomerApi(@PathVariable Long id) {
        return customerService.getCustomerById(id).orElse(null);
    }

    // API - create customer
    @PostMapping("/api")
    @ResponseBody
    public Customer createCustomerApi(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // API - update customer
    @PutMapping("/api/{id}")
    @ResponseBody
    public Customer updateCustomerApi(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setCustomerId(id);
        return customerService.saveCustomer(customer);
    }

    // API - delete customer
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteCustomerApi(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }




}