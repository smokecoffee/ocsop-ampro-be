package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    Optional<M00TaskJpo> findByProjectNumberContainsAndTaskNameContains(String projectNumber, String taskName);
}
