package com.poscdx.odc.ampro015.domain.entity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordResponse extends  MessageResponse {
  private boolean error;
}
