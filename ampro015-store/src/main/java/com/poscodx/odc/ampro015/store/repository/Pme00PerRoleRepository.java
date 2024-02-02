package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00PerRoleJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pme00PerRoleRepository
    extends JpaRepository<Pme00PerRoleJpo, Integer> {

    List<Pme00PerRoleJpo> findByRoleId(int id);

    List<Pme00PerRoleJpo> findByPermissionId(int id);

}
