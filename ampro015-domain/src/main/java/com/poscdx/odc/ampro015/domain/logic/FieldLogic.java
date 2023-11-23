package com.poscdx.odc.ampro015.domain.logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.store.FieldStore;

import java.util.Date;
import java.util.List;

public class FieldLogic implements FieldService {
    private final FieldStore store;

    public FieldLogic(FieldStore store) {
        this.store = store;
    }

    @Override
    public Field find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Field> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Field modify(int id, JsonElement updateInfoJson) {
        Field entity = this.store.retrieve(id);
        JsonObject jsonObject = updateInfoJson.getAsJsonObject();
        entity.setName(jsonObject.get("name").getAsString());
        entity.setValue(jsonObject.get("value").getAsString());
        entity.setSort(jsonObject.get("sort").getAsInt());
        entity.setAssetId(jsonObject.get("assetId").getAsInt());
        entity.setUpdateBy(jsonObject.get("updateBy").getAsInt());
        entity.setUpdateAt(new Date());
        return this.store.update(entity);
    }

    @Override
    public Field register(Field entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
