package com.app.helpdesk.services.execptions;

import java.io.Serial;

public class DataIntegrityViolationException extends RuntimeException{
  @Serial
  private static final long serialVersionUID = 1L;

  public DataIntegrityViolationException(String message) {
    super(message);
  }

  public DataIntegrityViolationException(String message, Throwable cause) {
    super(message, cause);
  }
}
