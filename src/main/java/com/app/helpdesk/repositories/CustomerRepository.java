package com.app.helpdesk.repositories;

import com.app.helpdesk.domain.Customer;
import com.app.helpdesk.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
