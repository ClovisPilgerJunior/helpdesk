package com.app.helpdesk.domain.enums;

public enum Status {
  OPENED(0, "OPENED"),
  GOING(1, "GOING"),
  CLOSED(2, "CLOSED");

  private Integer code;
  private String desc;

  Status(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Status toEnum(Integer code){
    if (code == null) {
      return null;
    }

    for (Status status : Status.values()) {
      if(code.equals(status.getCode())){
        return status;
      }
    }

    throw new IllegalArgumentException("Invalid Status");
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
