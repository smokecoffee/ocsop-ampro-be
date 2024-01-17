package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.ExcanUserJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExcanUserRepository
    extends JpaRepository<ExcanUserJpo, String>
{
    Boolean existsByName(String username);

    Boolean existsByEmail(String email);

    Optional<ExcanUserJpo> findByName(String userName);

    List<ExcanUserJpo> findAllByIdIn(List<String> id);
}
