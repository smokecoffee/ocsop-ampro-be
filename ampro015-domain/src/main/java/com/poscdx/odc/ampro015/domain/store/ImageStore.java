package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Image;

import java.util.List;

public interface ImageStore {
    Image retrieve(int id);

    List<Image> retrieveAll();

    Image update(Image entity);

    Image create(Image entity);

    void delete(int id);
}
