package com.app.helpdesk.repositories;

import com.app.helpdesk.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
