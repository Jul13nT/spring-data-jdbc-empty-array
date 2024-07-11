package com.test;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    private final JdbcAggregateOperations operations;

    public CustomerRepository(JdbcAggregateOperations operations) {
        this.operations = operations;
    }

    public Iterable<Customer> findAll(int page, int size, Sort sort) {
        return operations.findAll(Query.empty().with(PageRequest.of(page, size, sort)), Customer.class);
    }
}
