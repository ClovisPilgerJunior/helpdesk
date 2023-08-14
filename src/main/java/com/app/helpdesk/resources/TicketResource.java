package com.app.helpdesk.resources;

import com.app.helpdesk.domain.Ticket;
import com.app.helpdesk.domain.dtos.TicketDTO;
import com.app.helpdesk.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://helpdesk-front-ashy.vercel.app")
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

  @GetMapping
  public ResponseEntity<List<TicketDTO>> findAll(){
    List<Ticket> list = ticketService.findAll();
    List<TicketDTO> listDTO = list.stream().map(TicketDTO::new).toList();
    return ResponseEntity.ok().body(listDTO);
  }

  @PostMapping
  public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO ticketDTO){
    Ticket ticket = ticketService.create(ticketDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(ticket.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<TicketDTO> update(@PathVariable Integer id,@Valid @RequestBody TicketDTO ticketDTO){
    Ticket newTicket = ticketService.update(id, ticketDTO);
    return ResponseEntity.ok().body(new TicketDTO(newTicket));
  }
}
