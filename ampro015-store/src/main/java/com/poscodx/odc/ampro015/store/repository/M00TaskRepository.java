package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface M00TaskRepository extends JpaRepository<M00TaskJpo, M00TaskId> {
    @Query(value = "SELECT * FROM tb_m00_task WHERE PROJECT_NUMBER = :projectNumber", nativeQuery = true)
    List<M00TaskJpo> findAllByProjectNumber(@Param("projectNumber") String projectNumber, Pageable pageable);

    @Query(value = "SELECT * FROM tb_m00_task WHERE PROJECT_NUMBER = :projectNumber" + " AND TASK_NAME = :taskName", nativeQuery = true)
    Optional<M00TaskJpo> findOneTask(@Param("projectNumber") String projectNumber, @Param("taskName") String taskName);

}
