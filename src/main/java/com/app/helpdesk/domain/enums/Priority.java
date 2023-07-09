package com.app.helpdesk.domain.enums;

public enum Priority {
  LOW(0, "LOW"),
  MEDIUM(1, "MEDIUM"),
  HIGH(2, "HIGH");

  private Integer code;
  private String desc;

  Priority(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Priority toEnum(Integer code){
    if (code == null) {
      return null;
    }

    for (Priority status : Priority.values()) {
      if(code.equals(status.getCode())){
        return status;
      }
    }

    throw new IllegalArgumentException("Invalid priority");
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
