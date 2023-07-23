package com.app.helpdesk.domain.dtos;

import com.app.helpdesk.domain.Customer;
import com.app.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  protected Integer id;
  @NotNull(message = "Field NAME is required")
  protected String name;
  @NotNull(message = "Field CPF is required")
  protected String cpf;
  @NotNull(message = "Field EMAIL is required")
  protected String email;
  @NotNull(message = "Field PASSWORD is required")
  protected String password;
  protected Set<Integer> profiles = new HashSet<>();
  @JsonFormat(pattern = "dd/MM/yyyy")
  protected LocalDate createdAt = LocalDate.now();

  public CustomerDTO() {
    addProfile(Profile.CUSTOMER);
  }

  public CustomerDTO(Customer object) {
    this.id = object.getId();
    this.name = object.getName();
    this.cpf = object.getCpf();
    this.email = object.getEmail();
    this.password = object.getPassword();
    this.profiles = object.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
    this.createdAt = object.getCreatedAt();
    addProfile(Profile.CUSTOMER);
  }

  public Integer getId() {
    return id;
  }

  public CustomerDTO setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public CustomerDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getCpf() {
    return cpf;
  }

  public CustomerDTO setCpf(String cpf) {
    this.cpf = cpf;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public CustomerDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public CustomerDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public Set<Profile> getProfiles() {
    return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
  }

  public void addProfile(Profile profile) {
    this.profiles.add(profile.getCode());
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public CustomerDTO setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
    return this;
  }
}
