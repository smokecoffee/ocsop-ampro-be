package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.entity.Image;

import java.util.List;

public interface ImageStore {
    public List<Image> retrieve(Integer id);

    public List<Image> retrieveAll();

    public Image update(Image entity);

    public Image create(Image entity);

    public void delete(String id);
}
