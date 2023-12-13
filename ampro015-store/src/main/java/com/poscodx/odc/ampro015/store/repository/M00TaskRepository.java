package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.M00Task;
import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface M00TaskRepository extends JpaRepository<M00TaskJpo, M00TaskId> {
    //Pageable pageable);
    @Query(value = "SELECT t.PROJECT_NUMBER \n" +
            ", t.TASK_NAME \n" +
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
            //",tpet.CREATION_TIMESTAMP \n" +

            " FROM tb_m00_task AS t \n" +
            " WHERE 1=1 \n"+
            " AND (:planDate = '' or t.PLAN_DATE = :planDate)\n" +
            " AND (:actualEndDate = '' or t.ACTUAL_END_DATE = :actualEndDate )\n" +
            " AND (:projectNumber = '' or t.PROJECT_NUMBER = :projectNumber)\n" +
            " AND (:taskName = '' or t.TASK_NAME = :taskName)\n", nativeQuery = true)
    List<M00TaskJpo> findAllByProjectNumber(@Param("projectNumber") String projectNumber,
                                           @Param("actualEndDate") String actualEndDate,
                                           @Param("planDate") String planDate,
                                           @Param("taskName") String taskName,
                                           Pageable pageable);

    @Query(value = "SELECT * FROM tb_m00_task WHERE PROJECT_NUMBER = :projectNumber" + " AND TASK_NAME = :taskName", nativeQuery = true)
    Optional<M00TaskJpo> findOneTask(@Param("projectNumber") String projectNumber, @Param("taskName") String taskName);

}
