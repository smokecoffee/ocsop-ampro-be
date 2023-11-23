package com.poscdx.odc.ampro015.domain.spec;

import com.google.gson.JsonElement;
import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscoict.base.share.domain.NameValueList;

import java.util.List;

public interface FieldService {
    Field find(int id);

    List<Field> findAll();

    Field modify(int id, JsonElement updateInfoJson);

    Field register(Field entity);

    void remove(int id);
}
