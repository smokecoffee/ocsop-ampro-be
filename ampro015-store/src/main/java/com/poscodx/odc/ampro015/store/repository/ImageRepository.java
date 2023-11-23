package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageJpo, Image> {
}
