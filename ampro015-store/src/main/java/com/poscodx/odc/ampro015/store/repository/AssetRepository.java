package com.poscodx.odc.ampro015.store.repository;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscodx.odc.ampro015.store.jpo.AssetJpo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<AssetJpo, Asset> {

}
