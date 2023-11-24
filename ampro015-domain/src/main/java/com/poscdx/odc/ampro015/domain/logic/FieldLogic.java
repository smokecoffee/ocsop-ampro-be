package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Field;
import com.poscdx.odc.ampro015.domain.spec.FieldService;
import com.poscdx.odc.ampro015.domain.store.FieldStore;

import java.util.List;

public class FieldLogic implements FieldService {
    private final FieldStore store;

    public FieldLogic(FieldStore store) {
        this.store = store;
    }

    @Override
    public Field find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Field> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Field modify(Field field) {
        return this.store.update(field);
    }

    @Override
    public Field register(Field entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
