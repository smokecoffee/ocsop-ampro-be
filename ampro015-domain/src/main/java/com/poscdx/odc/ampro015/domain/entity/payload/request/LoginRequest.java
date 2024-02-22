package com.poscdx.odc.ampro015.domain.entity.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginRequest {
    @NotBlank
//  	private String name;
    private String id;

    @NotBlank
    private String password;
}
