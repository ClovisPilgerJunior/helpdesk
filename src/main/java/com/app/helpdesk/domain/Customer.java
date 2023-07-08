package com.app.helpdesk.domain;

import com.app.helpdesk.domain.enums.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jdk.jfr.Enabled;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Customer extends Person {
  @Serial
  private static final long serialVersionUID = 1L;
  @OneToMany(mappedBy = "customer")
  private List<Ticket> tickets = new ArrayList<>();

  public Customer(){
    addProfiles(Profile.CLIENTE);
  }

  public Customer(int id, String name, String cpf, String email, String password) {
    super(id, name, cpf, email, password);
    addProfiles(Profile.CLIENTE);
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public Customer setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }
}
