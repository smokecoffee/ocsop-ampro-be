package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.spec.AssetService;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.store.AssetStore;
import com.poscdx.odc.ampro015.domain.store.FieldStore;

import java.util.List;

public class FieldLogic implements FieldService {

    private final FieldStore store;

    public FieldLogic(FieldStore store) {
        this.store = store;
    }


    @Override
    public List<Field> findFieldInfos(Integer assetId) {
        return this.store.retrieve(assetId);
    }
}
