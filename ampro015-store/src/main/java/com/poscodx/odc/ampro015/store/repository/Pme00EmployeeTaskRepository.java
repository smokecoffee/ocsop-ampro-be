package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pme00EmployeeTaskRepository extends JpaRepository<Pme00EmployeeTaskJpo, M00TaskId> {

}
