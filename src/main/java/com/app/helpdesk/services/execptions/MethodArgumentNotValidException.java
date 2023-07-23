package com.app.helpdesk.services.execptions;

import java.io.Serial;

public class MethodArgumentNotValidException extends RuntimeException{
  @Serial
  private static final long serialVersionUID = 1L;

  public MethodArgumentNotValidException(String message) {
    super(message);
  }

  public MethodArgumentNotValidException(String message, Throwable cause) {
    super(message, cause);
  }
}
