package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.store.FieldStore;
import com.poscdx.odc.ampro015.domain.store.ImageStore;

import java.util.List;

public class ImageLogic implements ImageService {

    private final ImageStore store;

    public ImageLogic(ImageStore store) {
        this.store = store;
    }


    @Override
    public List<Image> findImageInfos(Integer assetId) {
        return this.store.retrieve(assetId);
    }
}
