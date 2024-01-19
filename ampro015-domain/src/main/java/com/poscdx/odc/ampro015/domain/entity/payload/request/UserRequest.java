package com.poscdx.odc.ampro015.domain.entity.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    @JsonProperty("username")
    private String userName;
    private String email;
    private String password;
    @JsonProperty("roles")
    private Set<String> roles;
}
