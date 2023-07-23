package com.app.helpdesk.repositories;

import com.app.helpdesk.domain.Customer;
import com.app.helpdesk.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
