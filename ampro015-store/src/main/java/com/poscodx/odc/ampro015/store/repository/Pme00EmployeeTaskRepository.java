package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Pme00EmployeeTaskRepository extends JpaRepository<Pme00EmployeeTaskJpo, M00EmployeeTaskId> {


    @Query("DELETE FROM tb_pme00_employee_task WHERE (PROJECT_NUMBER, TASK_NAME, EMP_ID, EMP_NAME) IN ")
    void deleteMultipleRowById(List<M00EmployeeTaskId> requestId);

}
