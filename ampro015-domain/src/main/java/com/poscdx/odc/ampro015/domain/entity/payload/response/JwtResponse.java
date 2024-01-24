package com.poscdx.odc.ampro015.domain.entity.payload.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private LoginUserInfo userInfo;
    private List<Map<Integer, String>> permissions;

    public JwtResponse(String accessToken, LoginUserInfo userInfo, List<Map<Integer, String>> permissions) {
        this.token = accessToken;
        this.userInfo = userInfo;
        this.permissions = permissions;
    }
}
