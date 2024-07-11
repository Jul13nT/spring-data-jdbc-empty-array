package com.test;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerResource {

    private final CustomerRepository customerRepository;

    public CustomerResource(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public Iterable<Customer> findAll(@RequestParam("page") int page, @RequestParam("size") int size,
        @RequestParam(name = "sortPropertyName") String sortPropertyName,
        @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        return customerRepository.findAll(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortPropertyName));
    }
}
