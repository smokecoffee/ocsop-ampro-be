package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.M00TaskJpo;
import com.poscdx.odc.ampro015.domain.entity.M00TaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface M00TaskRepository extends JpaRepository<M00TaskJpo, M00TaskId> {

}
