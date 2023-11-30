package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00Member;
import com.poscdx.odc.ampro015.domain.spec.Pme00MemberService;
import com.poscdx.odc.ampro015.domain.store.Pme00MemberStore;

import java.util.List;

public class Pme00MemberLogic implements Pme00MemberService {
    private final Pme00MemberStore store;

    public Pme00MemberLogic(Pme00MemberStore store) {
        this.store = store;
    }

    @Override
    public Pme00Member find(int id) {
        return this.store.retrieve(id);
    }

    @Override
    public List<Pme00Member> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00Member> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00Member register(Pme00Member entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(int id) {
        this.store.delete(id);
    }

}
