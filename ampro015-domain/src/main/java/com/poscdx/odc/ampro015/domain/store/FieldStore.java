package com.poscdx.odc.ampro015.domain.store;

import com.poscdx.odc.ampro015.domain.entity.Asset;
import com.poscdx.odc.ampro015.domain.entity.Field;

import java.util.List;

public interface FieldStore {
    public List<Field> retrieve(Integer id);

    public List<Field> retrieveAll();

    public Field update(Field entity);

    public Field create(Field entity);

    public void delete(String id);
}
