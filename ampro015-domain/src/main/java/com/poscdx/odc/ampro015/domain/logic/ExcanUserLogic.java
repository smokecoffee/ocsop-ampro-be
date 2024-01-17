package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.ExcanUser;
import com.poscdx.odc.ampro015.domain.spec.ExcanUserService;
import com.poscdx.odc.ampro015.domain.store.ExcanUserStore;

import java.util.List;

public class ExcanUserLogic implements ExcanUserService {
    private final ExcanUserStore store;

    public ExcanUserLogic(ExcanUserStore store) {
        this.store = store;
    }

    @Override
    public ExcanUser find(String id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<ExcanUser> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public ExcanUser register(ExcanUser entity) {
        return this.store.create(entity);
    }

    @Override
    public void modify(List<ExcanUser> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public void remove(String id) {
        this.store.delete(id);
    }

    @Override
    public boolean existsByName(String userName) {
        return this.store.existsByName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.store.existsByEmail(email);
    }

    @Override
    public List<ExcanUser> findAllByIdIn(List<String> id) {
        return this.store.findAllByIdIn(id);
    }
}
