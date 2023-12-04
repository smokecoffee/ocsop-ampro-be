package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.spec.ImageService;
import com.poscdx.odc.ampro015.domain.store.ImageStore;

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
    public Image modify(Image image) {
        return this.store.update(image);
    }

    @Override
    public Image register(Image entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

    @Override
    public List<Image> findImageInfos(Integer assetId) {
        return this.store.retrieveList(assetId);
    }

    @Override
    public List<Image> findByAssetId(int id) {
        return this.store.findByAssetId(id);
    }
}
