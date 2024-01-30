package com.poscdx.odc.ampro015.domain.entity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordResponse extends  MessageResponse {
  private int passwordTokenId;
  private boolean error;
  private String EmpId;
}
