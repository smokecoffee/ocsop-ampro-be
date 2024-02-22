package com.poscdx.odc.ampro015.domain.entity.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class ResetPasswordRequest {
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;
}
