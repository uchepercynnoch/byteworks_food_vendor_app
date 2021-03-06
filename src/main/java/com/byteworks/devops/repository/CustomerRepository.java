package com.byteworks.devops.repository;

import com.byteworks.devops.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
