package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.LogoutAccessToken;
import com.poscdx.odc.ampro015.domain.store.LogoutAccessTokenStore;
import com.poscodx.odc.ampro015.store.jpo.LogoutAccessTokenJpo;
import com.poscodx.odc.ampro015.store.repository.LogoutAccessTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LogoutAccessTokenJpaStore
    implements LogoutAccessTokenStore
{
    private final LogoutAccessTokenRepository repository;

    public LogoutAccessTokenJpaStore(LogoutAccessTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LogoutAccessToken> findByToken(String token) {

        return this.repository.findByToken(token).stream().map(LogoutAccessTokenJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public LogoutAccessToken register(LogoutAccessToken entity) {
        LogoutAccessTokenJpo logoutAccessTokenJpo = new LogoutAccessTokenJpo(entity);
        LogoutAccessTokenJpo savedJpo = this.repository.save(logoutAccessTokenJpo);
        return savedJpo.toDomain();
    }
}
