package com.app.helpdesk.domain.dtos;

import com.app.helpdesk.domain.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class TicketDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private Integer id;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate openingDate = LocalDate.now();
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate closingDate;
  @NotNull(message = "Field PRIORITY is required")
  private Integer priority;
  @NotNull(message = "Field STATUS is required")
  private Integer status;
  @NotNull(message = "Field TITLE is required")
  private String title;
  @NotNull(message = "Field OBSERVATIONS is required")
  private String observations;
  @NotNull(message = "Field TECHNICIAN is required")
  private Integer technical;
  @NotNull(message = "Field CUSTOMER is required")
  private Integer customer;
  private String technicianName;
  private String customerName;

  public TicketDTO() {
  }

  public TicketDTO(Ticket ticket) {
    this.id = ticket.getId();
    this.openingDate = ticket.getOpeningDate();
    this.closingDate = ticket.getClosingDate();
    this.priority = ticket.getPriority().getCode();
    this.status = ticket.getStatus().getCode();
    this.title = ticket.getTitle();
    this.observations = ticket.getObservations();
    this.technical = ticket.getTechnical().getId();
    this.customer = ticket.getCustomer().getId();
    this.technicianName = ticket.getTechnical().getName();
    this.customerName = ticket.getCustomer().getName();
  }

  public Integer getId() {
    return id;
  }

  public TicketDTO setId(Integer id) {
    this.id = id;
    return this;
  }

  public LocalDate getOpeningDate() {
    return openingDate;
  }

  public TicketDTO setOpeningDate(LocalDate openingDate) {
    this.openingDate = openingDate;
    return this;
  }

  public LocalDate getClosingDate() {
    return closingDate;
  }

  public TicketDTO setClosingDate(LocalDate closingDate) {
    this.closingDate = closingDate;
    return this;
  }

  public Integer getPriority() {
    return priority;
  }

  public TicketDTO setPriority(Integer priority) {
    this.priority = priority;
    return this;
  }

  public Integer getStatus() {
    return status;
  }

  public TicketDTO setStatus(Integer status) {
    this.status = status;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public TicketDTO setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getObservations() {
    return observations;
  }

  public TicketDTO setObservations(String observations) {
    this.observations = observations;
    return this;
  }

  public Integer getTechnical() {
    return technical;
  }

  public TicketDTO setTechnical(Integer technical) {
    this.technical = technical;
    return this;
  }

  public Integer getCustomer() {
    return customer;
  }

  public TicketDTO setCustomer(Integer customer) {
    this.customer = customer;
    return this;
  }

  public String getTechnicianName() {
    return technicianName;
  }

  public TicketDTO setTechnicianName(String technicianName) {
    this.technicianName = technicianName;
    return this;
  }

  public String getCustomerName() {
    return customerName;
  }

  public TicketDTO setCustomerName(String customerName) {
    this.customerName = customerName;
    return this;
  }
}
