package com.poscdx.odc.ampro015.domain.entity.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class ForgotPasswordRequest {
	@NotBlank
	private String email;
}
