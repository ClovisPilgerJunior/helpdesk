package com.app.helpdesk.domain;

import com.app.helpdesk.domain.dtos.CustomerDTO;
import com.app.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  public Customer(Integer id, String name, String cpf, String email, String password) {
    super(id, name, cpf, email, password);
    addProfiles(Profile.CUSTOMER);
  }

  public Customer(CustomerDTO object) {
    this.id = object.getId();
    this.name = object.getName();
    this.cpf = object.getCpf();
    this.email = object.getEmail();
    this.password = object.getPassword();
    this.profiles = object.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
    this.createdAt = object.getCreatedAt();
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public Customer setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
    return this;
  }
}
