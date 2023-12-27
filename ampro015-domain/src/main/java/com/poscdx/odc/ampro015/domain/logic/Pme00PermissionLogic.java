package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00Permission;
import com.poscdx.odc.ampro015.domain.spec.Pme00PermissionService;
import com.poscdx.odc.ampro015.domain.store.Pme00PermissionStore;

import java.util.List;

public class Pme00PermissionLogic implements Pme00PermissionService
{
    private final Pme00PermissionStore store;

    public Pme00PermissionLogic(Pme00PermissionStore store) {
        this.store = store;
    }

    @Override
    public Pme00Permission find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00Permission> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Pme00Permission register(Pme00Permission entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<Pme00Permission> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
