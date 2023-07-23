package com.app.helpdesk.services;

import com.app.helpdesk.domain.Ticket;
import com.app.helpdesk.repositories.TicketRepository;
import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  public Ticket findById(Integer id) {
    Optional<Ticket> ticket = ticketRepository.findById(id);
    return ticket.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID:" + id));
  }
}
