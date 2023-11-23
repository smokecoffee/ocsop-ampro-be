package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Image;

import java.util.List;

public interface ImageService {
    Image find(int id);

    List<Image> findAll();

    Image modify(Image image);

    Image register(Image entity);

    void remove(int id);
}
