package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;
import com.poscdx.odc.ampro015.domain.spec.ExcanAccessTokenService;
import com.poscdx.odc.ampro015.domain.store.ExcanAccessTokenStore;

import java.util.List;


public class ExcanAccessTokenLogic implements ExcanAccessTokenService
{
    private final ExcanAccessTokenStore store;

    public ExcanAccessTokenLogic(ExcanAccessTokenStore store) {
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
