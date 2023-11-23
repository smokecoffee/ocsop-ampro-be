package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.entity.Field;

import java.util.List;

public interface FieldService {

    List<Field> findFieldInfos(Integer assetId);
}
