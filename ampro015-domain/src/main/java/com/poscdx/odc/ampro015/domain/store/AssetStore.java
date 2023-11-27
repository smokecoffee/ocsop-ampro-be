package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Asset;

import java.util.List;

public interface AssetStore {
    Asset retrieve(int id);

    List<Asset> retrieveAll();

    Asset update(Asset entity);

    Asset create(Asset entity);

    void delete(int id);

    public List<Asset> retrieveByOwnerAndStatus(String emplName, int status);
}
