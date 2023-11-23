package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Asset;

import java.util.List;

public interface AssetStore {
    public Asset retrieve(String id);

    public List<Asset> retrieveAll();

    public List<Asset> retrieveByEmplNameAndStatus(String emplName, int status);

    public Asset update(Asset entity);

    public Asset create(Asset entity);

    public void delete(String id);
}
