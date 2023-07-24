package com.app.helpdesk.repositories;

import com.app.helpdesk.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

  boolean existsByCpf(String cpf);

  boolean existsByEmail(String email);

  Optional<Person> findByEmail(String email);
}
