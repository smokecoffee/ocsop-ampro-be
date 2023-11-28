package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.Pme00EmployeeMeetingId;
import com.poscodx.odc.ampro015.store.jpo.Pme00ProjectInfoJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pme00ProjectInfoRepository extends JpaRepository<Pme00ProjectInfoJpo, String> {

}
