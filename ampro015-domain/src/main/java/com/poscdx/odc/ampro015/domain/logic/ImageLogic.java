package com.poscdx.odc.ampro015.domain.logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import com.poscoict.base.share.domain.NameValueList;

import java.util.Date;
import java.util.List;

public class ImageLogic implements ImageService {
    private final ImageStore store;

    public ImageLogic(ImageStore store) {
        this.store = store;
    }

    @Override
    public Image find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Image> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Image modify(int id, JsonElement updateInfoJson) {
        Image entity = this.store.retrieve(id);
        JsonObject jsonObject = updateInfoJson.getAsJsonObject();
        entity.setName(jsonObject.get("name").getAsString());
        entity.setOriginalName(jsonObject.get("originalName").getAsString());
        entity.setPath(jsonObject.get("url").getAsString());
        entity.setAssetId(jsonObject.get("assetId").getAsInt());
        entity.setUpdateBy(jsonObject.get("updateBy").getAsInt());
        entity.setUpdateAt(new Date());
        return this.store.update(entity);
    }

    @Override
    public Image register(Image entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
