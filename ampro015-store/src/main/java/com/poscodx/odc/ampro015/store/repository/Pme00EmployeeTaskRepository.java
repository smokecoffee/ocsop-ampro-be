package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface Pme00EmployeeTaskRepository extends JpaRepository<Pme00EmployeeTaskJpo, M00EmployeeTaskId> {
    @Query(value = "SELECT * " +
            "FROM tb_pme00_employee_task " +
            "WHERE PROJECT_NUMBER = :projectNumber" +
            " AND (:taskName = '' OR TASK_NAME = :taskName)", nativeQuery = true)
    List<Pme00EmployeeTaskJpo> findAllByM00TaskId(@Param("projectNumber") String projectNumber, @Param("taskName") String taskName);

    @Query(value = "SELECT * " +
            "FROM tb_pme00_employee_task " +
            "WHERE PROJECT_NUMBER LIKE CONCAT('%',:projectNumber,'%')", nativeQuery = true)
    List<Pme00EmployeeTaskJpo> findAllByProjectNumber(@Param("projectNumber") String projectNumber);

    @Query(value = "SELECT * " +
            "FROM tb_pme00_employee_task " +
            "WHERE PROJECT_NUMBER IN :projectNumberSet", nativeQuery = true)
    List<Pme00EmployeeTaskJpo> findAllBySetProjectNumber(@Param("projectNumberSet") Set<String> projectNumberSet);
}
