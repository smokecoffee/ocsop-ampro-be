package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00PasswordTokenJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Pme00PasswordTokenRepository
    extends JpaRepository<Pme00PasswordTokenJpo, Integer> {

    Optional<Pme00PasswordTokenJpo> findById(int id);
}
