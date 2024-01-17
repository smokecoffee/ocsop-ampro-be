package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.ERole;
import com.poscodx.odc.ampro015.store.jpo.ExcanRoleJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExcanRoleRepository
    extends JpaRepository<ExcanRoleJpo, Integer>
{
    Optional<ExcanRoleJpo> findById(Integer id);

    List<ExcanRoleJpo> findByName(ERole name);

    Optional<ExcanRoleJpo> findByName(String name);
}
