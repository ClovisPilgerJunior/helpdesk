package com.app.helpdesk.services.execptions;

import java.io.Serial;

public class ConstraintViolationException extends RuntimeException{
  @Serial
  private static final long serialVersionUID = 1L;

  public ConstraintViolationException(String message) {
    super(message);
  }

  public ConstraintViolationException(String message, Throwable cause) {
    super(message, cause);
  }
}
