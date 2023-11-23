package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.store.AssetStore;

import java.util.List;

public class AssetLogic implements AssetService {

    private final AssetStore store;

    public AssetLogic(AssetStore store) {
        this.store = store;
    }


    @Override
    public List<Asset> findAssetInfos(String owner, int status) {
        return this.store.retrieveByEmplNameAndStatus(owner,status);
    }

    @Override
    public Asset retrieve(String id) {
        return this.store.retrieve(id);
    }
}
