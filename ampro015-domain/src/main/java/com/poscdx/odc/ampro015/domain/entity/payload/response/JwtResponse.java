package com.poscdx.odc.ampro015.domain.entity.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private LoginUserInfo userInfo;
  private List<String> permissions;

  public JwtResponse(String accessToken, LoginUserInfo userInfo, List<String> permissions) {
    this.token = accessToken;
    this.userInfo = userInfo;
    this.permissions = permissions;
  }
}
