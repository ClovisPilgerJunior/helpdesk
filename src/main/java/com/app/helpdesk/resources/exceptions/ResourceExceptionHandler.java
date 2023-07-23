package com.app.helpdesk.resources.exceptions;

import com.app.helpdesk.services.execptions.ConstraintViolationException;
import com.app.helpdesk.services.execptions.DataIntegrityViolationException;
import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler({ObjectNotFoundException.class})
  public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
    StandardError error = new StandardError(
        System.currentTimeMillis(),
        HttpStatus.NOT_FOUND.value(),
        "Object Not Found",
        ex.getMessage(),
        request.getRequestURI()
        );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
    StandardError error = new StandardError(
        System.currentTimeMillis(),
        HttpStatus.BAD_REQUEST.value(),
        "Violation of data integrity",
        ex.getMessage(),
        request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {

    ValidationError errors = new ValidationError(
        System.currentTimeMillis(),
        HttpStatus.BAD_REQUEST.value(),
        "Validation error",
        "Validation error in field",
        request.getRequestURI());

    for(FieldError error : ex.getBindingResult().getFieldErrors()){
      errors.addError(error.getField(), error.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
    StandardError error = new StandardError(
        System.currentTimeMillis(),
        HttpStatus.BAD_REQUEST.value(),
        "Invalid Brazilian Individual Taxpayer Registration (CPF) number",
        ex.getMessage(),
        request.getRequestURI()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
