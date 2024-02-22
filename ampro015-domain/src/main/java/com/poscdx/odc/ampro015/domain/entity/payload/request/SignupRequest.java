package com.poscdx.odc.ampro015.domain.entity.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
  private String id;
  @NotBlank
  @Size(min = 3, max = 20)
//  @JsonProperty("username")
  private String name;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private String role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private String phone;
  private String address;
  private String avatar;
  private String desc;
  private int status;
}
