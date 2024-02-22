package com.poscdx.odc.ampro015.domain.entity.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
public class ForgotPasswordRequest {
	@NotBlank
	private String email;
}
