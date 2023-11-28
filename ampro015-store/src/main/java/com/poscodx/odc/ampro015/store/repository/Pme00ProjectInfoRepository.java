package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectInfoJpo;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pme00ProjectInfoRepository extends JpaRepository<Pme00ProjectInfoJpo, String> {
    List<Pme00ProjectInfoJpo> findPme00ProjectInfoJpoBy(@Param(""))
}
