package com.poscdx.odc.ampro015.domain.entity.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class ResetPasswordRequest {
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
}
