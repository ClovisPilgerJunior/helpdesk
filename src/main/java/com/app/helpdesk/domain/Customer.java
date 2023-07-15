package com.app.helpdesk.domain;

import com.app.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Customer extends Person {
  @Serial
  private static final long serialVersionUID = 1L;
  @JsonIgnore
  @OneToMany(mappedBy = "customer")
  private List<Ticket> tickets = new ArrayList<>();

  public Customer(){
    addProfiles(Profile.CUSTOMER);
  }

  public Customer(String name, String cpf, String email, String password) {
    super(name, cpf, email, password);
    addProfiles(Profile.CUSTOMER);
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public Customer setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }
}
