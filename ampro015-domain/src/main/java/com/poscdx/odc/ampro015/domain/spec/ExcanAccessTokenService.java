package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.ExcanAccessToken;

import java.util.List;


public interface ExcanAccessTokenService {

    List<ExcanAccessToken> findByToken(String token);
    ExcanAccessToken register(ExcanAccessToken entity);
}
