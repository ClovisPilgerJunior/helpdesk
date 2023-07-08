package com.app.helpdesk.domain;

import com.app.helpdesk.domain.enums.Priority;
import com.app.helpdesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
@Entity
public class Ticket implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate openingDate = LocalDate.now();
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate closingDate = LocalDate.now();
  private Priority priority;
  private Status status;
  private String Title;
  private String observations;
  @ManyToOne
  @JoinColumn(name = "technical_id")
  private Technical technical;
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Ticket() {
  }

  public Ticket(int id, Priority priority, Status status, String title, String observations, Technical technical, Customer customer) {
    this.id = id;
    this.priority = priority;
    this.status = status;
    Title = title;
    this.observations = observations;
    this.technical = technical;
    this.customer = customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return id == ticket.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public int getId() {
    return id;
  }

  public Ticket setId(int id) {
    this.id = id;
    return this;
  }

  public LocalDate getOpeningDate() {
    return openingDate;
  }

  public Ticket setOpeningDate(LocalDate openingDate) {
    this.openingDate = openingDate;
    return this;
  }

  public LocalDate getClosingDate() {
    return closingDate;
  }

  public Ticket setClosingDate(LocalDate closingDate) {
    this.closingDate = closingDate;
    return this;
  }

  public Priority getPriority() {
    return priority;
  }

  public Ticket setPriority(Priority priority) {
    this.priority = priority;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public Ticket setStatus(Status status) {
    this.status = status;
    return this;
  }

  public String getTitle() {
    return Title;
  }

  public Ticket setTitle(String title) {
    Title = title;
    return this;
  }

  public String getObservations() {
    return observations;
  }

  public Ticket setObservations(String observations) {
    this.observations = observations;
    return this;
  }

  public Technical getTechnical() {
    return technical;
  }

  public Ticket setTechnical(Technical technical) {
    this.technical = technical;
    return this;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Ticket setCustomer(Customer customer) {
    this.customer = customer;
    return this;
  }
}
