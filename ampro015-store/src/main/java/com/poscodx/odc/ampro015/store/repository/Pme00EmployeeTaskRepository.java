package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00EmployeeTaskId;
import com.poscodx.odc.ampro015.store.jpo.Pme00EmployeeTaskJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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


    @Query(value = "SELECT \n " +
            "emp.PROJECT_NUMBER, \n"+
            "emp.TASK_NAME, \n"+
            "emp.EMP_ID, \n"+
            "emp.EMP_NAME, \n"+
            "emp.AVATAR, \n"+
            "t.TASK_EXPLAIN, \n"+
            "t.STATUS, \n"+
            "t.PLAN_DATE, \n"+
            "t.ACTUAL_END_DATE, \n"+
            "t.REMARK, \n"+
            "t.LAST_UPDATE_TIMESTAMP, \n"+
            "t.LAST_UPDATE_ID, \n"+
            "t.WRITER, \n"+
            "t.PASSWORD, \n"+
            "t.CATEGORY \n" +
            "FROM tb_pme00_employee_task AS emp \n"
            +" LEFT JOIN tb_m00_task AS t ON emp.PROJECT_NUMBER = t.PROJECT_NUMBER and emp.TASK_NAME = t.TASK_NAME \n"
            +" WHERE emp.EMP_ID = :employeeId \n "
    , nativeQuery = true)
    List<Object[]> findAllEmployeeId(@Param("employeeId") String employeeId);
}
