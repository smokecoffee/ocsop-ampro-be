package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00PasswordJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Pme00PasswordRepository
    extends JpaRepository<Pme00PasswordJpo, Integer> {

    Optional<Pme00PasswordJpo> findById(int id);
}
