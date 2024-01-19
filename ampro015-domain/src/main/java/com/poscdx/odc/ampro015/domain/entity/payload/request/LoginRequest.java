package com.poscdx.odc.ampro015.domain.entity.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	@NotBlank
//  	private String name;
	private String id;

	@NotBlank
	private String password;
}
