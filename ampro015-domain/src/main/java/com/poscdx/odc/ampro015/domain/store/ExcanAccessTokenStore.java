package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.ExcanAccessToken;

import java.util.List;


public interface ExcanAccessTokenStore {
    List<ExcanAccessToken> findByToken(String token);
    ExcanAccessToken register(ExcanAccessToken entity);
}
