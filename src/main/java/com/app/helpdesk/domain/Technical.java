package com.app.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Technical extends Person {
  private List<Ticket> tickets = new ArrayList<>();

  public Technical(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public Technical(int id, String name, String cpf, String email, String password) {
    super(id, name, cpf, email, password);
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public Technical setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }
}
