package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;

import java.util.List;


public interface LogoutAccessTokenStore {
    List<LogoutAccessToken> findByToken(String token);
    LogoutAccessToken register(LogoutAccessToken entity);
}
