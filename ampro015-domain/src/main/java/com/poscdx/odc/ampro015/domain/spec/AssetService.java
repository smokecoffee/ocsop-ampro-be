package com.poscdx.odc.ampro015.domain.spec;

import com.google.gson.JsonElement;
import com.poscdx.odc.ampro015.domain.entity.Asset;

import java.util.List;

public interface AssetService {

    Asset find(int id);

    List<Asset> findAll();

    Asset modify(int id, JsonElement updateInfoJson);

    Asset register(Asset entity);

    void remove(int id);
}
