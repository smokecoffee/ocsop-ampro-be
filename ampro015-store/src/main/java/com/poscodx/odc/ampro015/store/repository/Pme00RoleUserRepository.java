package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00RoleUserJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Pme00RoleUserRepository extends JpaRepository<Pme00RoleUserJpo, Integer> {

    List<Pme00RoleUserJpo> findByRoleId(int id);

    List<Pme00RoleUserJpo> findByEmpId(String id);
}
