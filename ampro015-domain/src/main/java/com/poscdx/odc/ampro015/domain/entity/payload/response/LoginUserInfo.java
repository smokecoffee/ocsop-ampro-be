package com.poscdx.odc.ampro015.domain.entity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {
    private String id;
    private String username;
    private String email;
    private String roleId;
    private String avatar;
}
