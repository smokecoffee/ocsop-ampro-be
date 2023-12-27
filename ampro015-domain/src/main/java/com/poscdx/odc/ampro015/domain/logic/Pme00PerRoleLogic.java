
package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00PerRole;
import com.poscdx.odc.ampro015.domain.spec.Pme00PerRoleService;
import com.poscdx.odc.ampro015.domain.store.Pme00PerRoleStore;

import java.util.List;

public class Pme00PerRoleLogic implements Pme00PerRoleService
{
    private final Pme00PerRoleStore store;

    public Pme00PerRoleLogic(Pme00PerRoleStore store) {
        this.store = store;
    }

    @Override
    public Pme00PerRole find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00PerRole> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Pme00PerRole register(Pme00PerRole entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<Pme00PerRole> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
