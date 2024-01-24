package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00Password;
import com.poscdx.odc.ampro015.domain.spec.Pme00PasswordService;
import com.poscdx.odc.ampro015.domain.store.Pme00PasswordStore;

import java.util.List;

public class Pme00PasswordLogic implements Pme00PasswordService
{
    private final Pme00PasswordStore store;

    public Pme00PasswordLogic(Pme00PasswordStore store) {
        this.store = store;
    }

    @Override
    public Pme00Password find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00Password> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Pme00Password register(Pme00Password entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<Pme00Password> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
