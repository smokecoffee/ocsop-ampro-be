package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00PasswordToken;
import com.poscdx.odc.ampro015.domain.spec.Pme00PasswordTokenService;
import com.poscdx.odc.ampro015.domain.store.Pme00PasswordTokenStore;

import java.util.List;

public class Pme00PasswordTokenLogic implements Pme00PasswordTokenService
{
    private final Pme00PasswordTokenStore store;

    public Pme00PasswordTokenLogic(Pme00PasswordTokenStore store) {
        this.store = store;
    }

    @Override
    public Pme00PasswordToken find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00PasswordToken> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Pme00PasswordToken register(Pme00PasswordToken entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<Pme00PasswordToken> entityList) {
        entityList.forEach( this.store::update);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }
}
