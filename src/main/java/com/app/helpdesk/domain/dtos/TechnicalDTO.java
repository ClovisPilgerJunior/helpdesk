package com.app.helpdesk.domain.dtos;

import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicalDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  protected Integer id;
  protected String name;
  protected String cpf;
  protected String email;
  protected String password;
  protected Set<Integer> profiles = new HashSet<>();
  @JsonFormat(pattern = "dd/MM/yyyy")
  protected LocalDate createdAt = LocalDate.now();

  public TechnicalDTO() {
  }

  public TechnicalDTO(Technical object) {
    this.id = object.getId();
    this.name = object.getName();
    this.cpf = object.getCpf();
    this.email = object.getEmail();
    this.password = object.getPassword();
    this.profiles = object.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
    this.createdAt = object.getCreatedAt();
  }

  public String getName() {
    return name;
  }

  public TechnicalDTO setName(String name) {
    this.name = name;
    return this;
  }

  public String getCpf() {
    return cpf;
  }

  public TechnicalDTO setCpf(String cpf) {
    this.cpf = cpf;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public TechnicalDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public TechnicalDTO setPassword(String password) {
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

  public TechnicalDTO setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
    return this;
  }
}
