package com.poscodx.odc.ampro015.store;

import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscdx.odc.ampro015.domain.store.ImageStore;
import com.poscodx.odc.ampro015.store.jpo.ImageJpo;
import com.poscodx.odc.ampro015.store.repository.ImageRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ImageJpaStore implements ImageStore {
    private final ImageRepository repository;

    public ImageJpaStore(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Image> retrieve(Integer assetId) {
        return ImageJpo.toDomains(this.repository.findAllByAssetId(assetId));
    }

    @Override
    public List<Image> retrieveAll() {
        return null;
    }

    @Override
    public Image update(Image entity) {
        return null;
    }

    @Override
    public Image create(Image entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
