package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;

import java.util.List;


public interface LogoutAccessTokenService {

    List<LogoutAccessToken> findByToken(String token);
    LogoutAccessToken register(LogoutAccessToken entity);
}
