package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscodx.odc.ampro015.store.jpo.AssetJpo;
import com.poscodx.odc.ampro015.store.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AssetJpaStore implements AssetStore {

    @Autowired AssetRepository assetRepository;

    @Override
    public Asset add(Asset asset) {
        AssetJpo assetJpo = new AssetJpo(asset);
        AssetJpo addAsset = assetRepository.save(assetJpo);
        return addAsset.toDomain();
    }
}
