package com.poscdx.odc.ampro015.domain.logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscoict.base.share.domain.NameValueList;

import java.util.Date;
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
    public Asset modify(int id, JsonElement updateInfoJson) {
        Asset entity = this.store.retrieve(id);
        JsonObject jsonObject = updateInfoJson.getAsJsonObject();
        entity.setToken(jsonObject.get("token").getAsString());
        entity.setOwner(jsonObject.get("owner").getAsString());
        entity.setDuration(jsonObject.get("duration").getAsInt());
        entity.setQrcode(jsonObject.get("qrcode").getAsString());
        entity.setStatus(jsonObject.get("status").getAsInt());
        entity.setUpdateBy(jsonObject.get("updateBy").getAsInt());
        entity.setUpdateAt(new Date());
        return this.store.update(entity);
    }

    @Override
    public Asset register(Asset entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
