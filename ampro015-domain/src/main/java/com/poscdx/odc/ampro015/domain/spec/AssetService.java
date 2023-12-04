package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Asset;

import java.util.List;

public interface AssetService {

    Asset find(int id);

    List<Asset> findAll();

    Asset modify(Asset asset);

    Asset register(Asset entity);

    void remove(int id);

    List<Asset> findAssetInfos(String emplName, int status);

    Asset findByToken(String token);

}
