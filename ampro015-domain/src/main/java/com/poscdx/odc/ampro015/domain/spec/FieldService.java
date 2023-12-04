package com.poscdx.odc.ampro015.domain.spec;

import com.poscdx.odc.ampro015.domain.entity.Field;

import java.util.List;

public interface FieldService {
    Field find(int id);

    List<Field> findAll();

    Field modify(Field field);

    Field register(Field entity);

    void remove(int id);

    List<Field> findFieldInfos(Integer assetId);

    List<Field> findByAssetId(int id);
}
