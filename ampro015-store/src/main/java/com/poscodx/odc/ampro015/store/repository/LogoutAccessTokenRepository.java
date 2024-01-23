package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.LogoutAccessTokenJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogoutAccessTokenRepository
    extends JpaRepository<LogoutAccessTokenJpo, Integer>
{
    List<LogoutAccessTokenJpo> findByToken(String id);
}
