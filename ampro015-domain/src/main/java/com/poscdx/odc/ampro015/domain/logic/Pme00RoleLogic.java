package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00Role;
import com.poscdx.odc.ampro015.domain.spec.Pme00RoleService;
import com.poscdx.odc.ampro015.domain.store.Pme00RoleStore;

import java.util.List;

public class Pme00RoleLogic implements Pme00RoleService {
    private final Pme00RoleStore store;

    public Pme00RoleLogic(Pme00RoleStore store) {
        this.store = store;
    }

    @Override
    public Pme00Role find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00Role> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00Role> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00Role register(Pme00Role entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

}
