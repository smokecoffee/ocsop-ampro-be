package com.poscdx.odc.ampro015.domain.spec;

import com.google.gson.JsonElement;
import com.poscdx.odc.ampro015.domain.entity.Image;
import com.poscoict.base.share.domain.NameValueList;

import java.util.List;

public interface ImageService {
    Image find(int id);

    List<Image> findAll();

    Image modify(int id, JsonElement updateInfoJson);

    Image register(Image entity);

    void remove(int id);
}
