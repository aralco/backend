package com.dev.backend.rest;

import com.dev.backend.model.Customer;
import com.dev.backend.rest.wrapper.CommonResponse;
import com.dev.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer",
        consumes = "application/json",
        produces = "application/json")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public ResponseEntity<Customer> viewCustomer(@PathVariable String code) {
        return new ResponseEntity<>(customerService.getCustomer(code), HttpStatus.OK);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponse> deleteCustomer(@PathVariable String code) {
        String message = customerService.deleteCustomer(code);
        return new ResponseEntity<>(new CommonResponse(message), HttpStatus.OK);
    }
    
}
