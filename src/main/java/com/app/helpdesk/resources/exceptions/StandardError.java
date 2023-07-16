package com.app.helpdesk.resources.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class StandardError implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  private Long timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;

  public StandardError() {
  }

  public StandardError(Long timestamp, Integer status, String error, String message, String path) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public StandardError setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public Integer getStatus() {
    return status;
  }

  public StandardError setStatus(Integer status) {
    this.status = status;
    return this;
  }

  public String getError() {
    return error;
  }

  public StandardError setError(String error) {
    this.error = error;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public StandardError setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getPath() {
    return path;
  }

  public StandardError setPath(String path) {
    this.path = path;
    return this;
  }
}
