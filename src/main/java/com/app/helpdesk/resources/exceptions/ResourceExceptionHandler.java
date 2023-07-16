package com.app.helpdesk.resources.exceptions;

import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler({ObjectNotFoundException.class})
  public ResponseEntity<StandardError> objectNotFoundExecption(ObjectNotFoundException ex, HttpServletRequest request) {
    StandardError error = new StandardError(
        System.currentTimeMillis(),
        HttpStatus.NOT_FOUND.value(),
        "Object Not Found",
        ex.getMessage(),
        request.getRequestURI()
        );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
}
