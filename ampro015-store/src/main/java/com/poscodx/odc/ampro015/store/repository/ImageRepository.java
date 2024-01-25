package com.poscodx.odc.ampro015.store.repository;

import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageJpo, Integer> {

    List<ImageJpo> findByAssetId(int assetId);

    List<ImageJpo> findByAssetIdAndDeleteAtIsNull(int assetId);

}
