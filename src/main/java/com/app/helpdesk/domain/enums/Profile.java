package com.app.helpdesk.domain.enums;

public enum Profile {
  ADMIN(0, "ROLE_ADMIN"),
  CLIENTE(1, "ROLE_CUSTOMER"),
  TECNICO(2, "ROLE_TECHNICAL");

  private Integer code;
  private String desc;

  Profile(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public static Profile toEnum(Integer code){
    if (code == null) {
      return null;
    }

    for (Profile profile : Profile.values()) {
      if(code.equals(profile.getCode())){
        return profile;
      }
    }

    throw new IllegalArgumentException("Invalid Profile");
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
