package com.app.helpdesk.resources.exceptions;

import com.app.helpdesk.domain.Ticket;
import com.app.helpdesk.domain.dtos.TicketDTO;
import com.app.helpdesk.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ticket")
public class TicketResource {

  @Autowired
  private TicketService ticketService;
  @GetMapping(value = "/{id}")
  public ResponseEntity<TicketDTO> findById(@PathVariable Integer id){
    Ticket ticket = ticketService.findById(id);
    return ResponseEntity.ok().body(new TicketDTO(ticket));
  }
}
