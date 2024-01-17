
package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.ExcanPerRole;
import com.poscdx.odc.ampro015.domain.spec.ExcanPerRoleService;
import com.poscdx.odc.ampro015.domain.store.ExcanPerRoleStore;

import java.util.List;

public class ExcanPerRoleLogic implements ExcanPerRoleService
{
    private final ExcanPerRoleStore store;

    public ExcanPerRoleLogic(ExcanPerRoleStore store) {
        this.store = store;
    }

    @Override
    public ExcanPerRole find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<ExcanPerRole> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public ExcanPerRole register(ExcanPerRole entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<ExcanPerRole> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
