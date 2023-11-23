package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.entity.Image;

import java.util.List;

public interface ImageService {

    List<Image> findImageInfos(Integer assetId);
}
