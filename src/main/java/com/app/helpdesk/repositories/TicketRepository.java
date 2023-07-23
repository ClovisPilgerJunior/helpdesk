package com.app.helpdesk.repositories;

import com.app.helpdesk.domain.Person;
import com.app.helpdesk.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
