package dev.factory.rest;

import dev.factory.model.Customer;
import dev.factory.rest.wrapper.CommonResponse;
import dev.factory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers",
        consumes = "application/json",
        produces = "application/json")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {
        return customerService.getCustomers();
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public Customer viewCustomer(@PathVariable String code) {
        return customerService.getCustomer(code);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public CommonResponse deleteCustomer(@PathVariable String code) {
        String message = customerService.deleteCustomer(code);
        return new CommonResponse(message);
    }
    
}
