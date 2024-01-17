package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.ExcanPerRoleJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcanPerRoleRepository
    extends JpaRepository<ExcanPerRoleJpo, Integer>
{
   //List<ExcanPerRoleJpo> findByRoleId(int id);

    List<ExcanPerRoleJpo> findByName(String id);

}
