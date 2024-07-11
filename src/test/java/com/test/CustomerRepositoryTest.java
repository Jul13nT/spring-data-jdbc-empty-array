package com.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.test.database.replace=none",
    "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db?TC_INITSCRIPT=schema.sql"
})
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Sql("classpath:data.sql")
    void testNames() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll(0, 10, Sort.by(Sort.Direction.ASC, "id"));

        assertThat(customers).hasSize(3);

        Customer firstCustomer = customers.get(0);
        assertThat(firstCustomer.getId()).isNotNull();
        assertThat(firstCustomer.getNames()).hasSize(2);

        Customer secondCustomer = customers.get(1);
        assertThat(secondCustomer.getId()).isNotNull();
        assertThat(secondCustomer.getNames()).isNull();

        Customer thirdCustomer = customers.get(2);
        assertThat(thirdCustomer.getId()).isNotNull();
        assertThat(thirdCustomer.getNames()).hasSize(0);
    }
}
