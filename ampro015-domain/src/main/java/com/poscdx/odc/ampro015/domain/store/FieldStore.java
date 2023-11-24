package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Field;

import java.util.List;

public interface FieldStore {
    Field retrieve(int id);

    List<Field> retrieveAll();

    Field update(Field entity);

    Field create(Field entity);

    void delete(int id);
}
