package com.app.helpdesk.domain;

import com.app.helpdesk.domain.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Technical extends Person {
  @Serial
  private static final long serialVersionUID = 1L;
  @OneToMany(mappedBy = "technical")
  private List<Ticket> tickets = new ArrayList<>();

  public Technical() {
    super();
    addProfiles(Profile.CLIENTE);
  }

  public Technical(String name, String cpf, String email, String password) {
    super(name, cpf, email, password);
    addProfiles(Profile.CLIENTE);
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public Technical setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }
}
