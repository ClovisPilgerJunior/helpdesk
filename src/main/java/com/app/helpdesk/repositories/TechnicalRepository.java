package com.app.helpdesk.repositories;

import com.app.helpdesk.domain.Person;
import com.app.helpdesk.domain.Technical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalRepository extends JpaRepository<Technical, Integer> {
}
