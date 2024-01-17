package com.poscdx.odc.ampro015.domain.entity;

public enum ERole {


  ROLE_ADMIN(1),
  ROLE_STAFF(2),
  ROLE_USER(3);

  private int value;

  ERole(int value) {
    this.value = value;
  }

  public int getValue(){
    return this.value;
  }
}
