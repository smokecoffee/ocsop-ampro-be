package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.store.AssetStore;

import java.util.ArrayList;
import java.util.List;

public class AssetLogic implements AssetService {
    private final AssetStore store;

    public AssetLogic(AssetStore store) {
        this.store = store;
    }

    @Override
    public Asset find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Asset> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Asset modify(Asset asset) {
        return this.store.update(asset);
    }

    @Override
    public Asset register(Asset entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    @Override
    public List<Asset> findAssetInfos(String owner, int status) {
        return this.store.retrieveByOwnerAndStatus(owner, status);
    }

    @Override
    public Asset findByToken(String token) {
        return this.store.findByToken(token);
    }

    @Override
    public List<Asset> findByAssetAndOwnerAndStatus(int assetId, String emplId, int status) {
        List<Object[]> resultList = this.store.findByAssetAndOwnerAndStatus(assetId, emplId, status);
        List<Asset> AssetInfoList = new ArrayList<>();
        for(Object[] obj : resultList){
            AssetInfoList.add(new Asset(obj));
        }
        return AssetInfoList;
    }

}
