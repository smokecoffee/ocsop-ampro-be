package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.ExcanPermission;
import com.poscdx.odc.ampro015.domain.spec.ExcanPermissionService;
import com.poscdx.odc.ampro015.domain.store.ExcanPermissionStore;

import java.util.List;

public class ExcanPermissionLogic implements ExcanPermissionService
{
    private final ExcanPermissionStore store;

    public ExcanPermissionLogic(ExcanPermissionStore store) {
        this.store = store;
    }

    @Override
    public ExcanPermission find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<ExcanPermission> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public ExcanPermission register(ExcanPermission entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<ExcanPermission> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
