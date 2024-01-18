package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00PermissionJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Pme00PermissionRepository
    extends JpaRepository<Pme00PermissionJpo, Integer> {

    Optional<Pme00PermissionJpo> findById(int id);
}
