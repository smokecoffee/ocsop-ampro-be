package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface M00TaskRepository extends JpaRepository<M00TaskJpo, M00TaskId> {
    @Query(value = "SELECT t.PROJECT_NUMBER \n" +
            ",t.TASK_NAME \n" +
            ",t.CATEGORY \n" +
            ",t.EMP_ID \n" +
            ",t.LAST_UPDATE_ID \n" +
            ",t.PASSWORD \n" +
            ",t.REMARK \n" +
            ",t.STATUS \n" +
            ",t.TASK_EXPLAIN \n" +
            ",t.WRITER \n" +
            ",t.PLAN_DATE \n" +
            ",t.ACTUAL_END_DATE \n" +
            ",t.LAST_UPDATE_TIMESTAMP \n" +
            ",t.CREATION_TIMESTAMP \n" +
            ",t.START_DATE \n" +
            ",t.STATUS_APPROVE \n" +
            " FROM tb_m00_task AS t \n" +
            " WHERE 1=1 \n"+
            " AND (:projectNumber IS NULL OR t.PROJECT_NUMBER LIKE :projectNumber)\n" +
            " AND (:taskName IS NULL OR t.TASK_NAME LIKE CONCAT('%',:taskName, '%'))\n" +
            " AND (:planDate IS NULL OR t.PLAN_DATE LIKE :planDate)\n" +
            " AND (:category IS NULL OR t.CATEGORY = :category)\n" +
            " AND (:empId IS NULL OR t.EMP_ID = :empId)\n" +
            " AND (:actualEndDate IS NULL OR t.ACTUAL_END_DATE LIKE :actualEndDate)\n" +
            " AND (:status IS NULL OR t.STATUS = :status)\n", nativeQuery = true)
    List<M00TaskJpo> findTaskByConditions(@Param("projectNumber") String projectNumber,
                                          @Param("taskName") String taskName,
                                          @Param("planDate") String planDate,
                                          @Param("actualEndDate") String actualEndDate,
                                          @Param("status") String status,
                                          @Param("empId") String empId,
                                          @Param("category") String category);

    List<M00TaskJpo> findAllByProjectNumberContains(String projectNumber);

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
            "t.CATEGORY, \n" +
            "t.CREATION_TIMESTAMP, \n"+
            "t.START_DATE \n" +
            "t.STATUS_APPROVE \n" +
            "FROM tb_m00_task AS t \n"
            +" JOIN tb_pme00_employee_task AS emp ON t.PROJECT_NUMBER = emp.PROJECT_NUMBER and t.TASK_NAME = emp.TASK_NAME \n"
            +" WHERE emp.EMP_ID = :employeeId \n "
            +" AND (:projectNumber IS NULL OR t.PROJECT_NUMBER LIKE :projectNumber)\n"
            +" AND (:taskName IS NULL OR t.TASK_NAME LIKE CONCAT('%',:taskName, '%'))\n"
            +" AND (:status IS NULL OR t.STATUS = :status) \n", nativeQuery = true)
    List<Object[]> findAllTaskByEmpId(@Param("projectNumber") String projectNumber,
                                      @Param("taskName") String taskName,
                                      @Param("status") String status,
                                      @Param("employeeId") String employeeId);

    @Query(value = "SELECT t.PROJECT_NUMBER \n" +
            ",t.TASK_NAME \n" +
            ",t.CATEGORY \n" +
            ",t.EMP_ID \n" +
            ",t.LAST_UPDATE_ID \n" +
            ",t.PASSWORD \n" +
            ",t.REMARK \n" +
            ",t.STATUS \n" +
            ",t.TASK_EXPLAIN \n" +
            ",t.WRITER \n" +
            ",t.PLAN_DATE \n" +
            ",t.ACTUAL_END_DATE \n" +
            ",t.LAST_UPDATE_TIMESTAMP \n" +
            ",t.CREATION_TIMESTAMP \n" +
            ",t.START_DATE \n" +
            " FROM tb_m00_task AS t \n" +
            " WHERE 1=1 \n"+
            " AND (:projectNumber IS NULL OR t.PROJECT_NUMBER LIKE :projectNumber)\n" +
            " AND (:taskName IS NULL OR t.TASK_NAME LIKE CONCAT('%',:taskName, '%'))\n" +
            " AND (:planFrom IS NULL OR t.PLAN_DATE >= :planFrom)\n" +
            " AND (:planTo IS NULL OR t.PLAN_DATE <= :planTo)\n" +
            " AND (:actualFrom IS NULL OR t.ACTUAL_END_DATE >= :actualFrom)\n" +
            " AND (:actualTo IS NULL OR t.ACTUAL_END_DATE <= :actualTo)\n" +
            " AND (:startDateFrom IS NULL OR t.START_DATE >= :startDateFrom)\n" +
            " AND (:startDateTo IS NULL OR t.START_DATE <= :startDateTo)\n" +
            " AND (:category IS NULL OR t.CATEGORY = :category)\n" +
            " AND (:empId IS NULL OR t.EMP_ID = :empId)\n" +
            " AND (:status IS NULL OR t.STATUS = :status)\n", nativeQuery = true)
    List<M00TaskJpo> searchTask(@Param("projectNumber") String projectNumber,
                                @Param("taskName") String taskName,
                                @Param("planFrom") String planFrom,
                                @Param("planTo") String planTo,
                                @Param("actualFrom") String actualFrom,
                                @Param("actualTo") String actualTo,
                                @Param("startDateFrom") String startDateFrom,
                                @Param("startDateTo") String startDateTo,
                                @Param("status") String status,
                                @Param("empId") String empId,
                                @Param("category") String category);
}
