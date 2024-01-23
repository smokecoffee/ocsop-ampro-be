package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00RoleJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Pme00RoleRepository extends JpaRepository<Pme00RoleJpo, Integer> {

    Optional<Pme00RoleJpo> findById(Integer id);

    Optional<Pme00RoleJpo> findByName(String name);
}
