package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscodx.odc.ampro015.store.jpo.FieldJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<FieldJpo, Field> {
}
