package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Pme00EmployeeTaskRepository extends JpaRepository<Pme00EmployeeTaskJpo, M00EmployeeTaskId> {


    @Query(value = "DELETE FROM tb_pme00_employee_task WHERE (PROJECT_NUMBER, TASK_NAME, EMP_ID, EMP_NAME) IN  :requestId", nativeQuery = true)
    void deleteMultipleRowById(@Param("requestId") String requestId);


    @Query(value = "SELECT * FROM tb_pme00_employee_task WHERE PROJECT_NUMBER = :projectNumber" + " AND TASK_NAME = :taskName", nativeQuery = true)
    List<Pme00EmployeeTaskJpo> findAllByM00TaskId(@Param("projectNumber") String projectNumber, @Param("taskName") String taskName);

}
