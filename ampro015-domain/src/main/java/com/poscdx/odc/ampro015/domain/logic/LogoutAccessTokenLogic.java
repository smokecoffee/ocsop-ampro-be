package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;
import com.poscdx.odc.ampro015.domain.spec.LogoutAccessTokenService;
import com.poscdx.odc.ampro015.domain.store.LogoutAccessTokenStore;

import java.util.List;


public class LogoutAccessTokenLogic implements LogoutAccessTokenService
{
    private final LogoutAccessTokenStore store;

    public LogoutAccessTokenLogic(LogoutAccessTokenStore store) {
        this.store = store;
    }

    @Override
    public List<LogoutAccessToken> findByToken(String token) {
        return this.store.findByToken(token);
    }

    @Override
    public LogoutAccessToken register(LogoutAccessToken entity) {
        return this.store.register(entity);
    }
}
