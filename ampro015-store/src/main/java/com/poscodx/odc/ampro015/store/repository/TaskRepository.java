package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.Pme00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.TaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Pme00TaskJpo, TaskId> {

    Optional<Pme00TaskJpo> findAllByProjectNumberAndTaskName(@Param("projectNumber") String projectName, @Param("taskName") String taskName);
}
