package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface M00TaskRepository extends JpaRepository<M00TaskJpo, M00TaskId>, JpaSpecificationExecutor<M00TaskJpo> {
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
            " AND (:projectNumber = '' OR t.PROJECT_NUMBER = :projectNumber)\n" +
            " AND (:taskName = '' OR t.TASK_NAME = :taskName)\n" +
            " AND (:planDate = '' OR t.PLAN_DATE = :planDate)\n" +
            " AND (:actualEndDate = '' OR t.ACTUAL_END_DATE = :actualEndDate)\n", nativeQuery = true)
    Page<M00TaskJpo> findTaskByConditions(@Param("projectNumber") String projectNumber,
                                          @Param("taskName") String taskName,
                                          @Param("planDate") String planDate,
                                          @Param("actualEndDate") String actualEndDate, Pageable pageable);
    
    List<M00TaskJpo> findAllByProjectNumberContains(String projectNumber);

    Optional<M00TaskJpo> findByProjectNumberContainsAndTaskNameContains(String projectNumber, String taskName);
}
