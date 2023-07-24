package com.app.helpdesk.domain.dtos;

public class CredentialDTO {

  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public CredentialDTO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public CredentialDTO setPassword(String password) {
    this.password = password;
    return this;
  }
}
