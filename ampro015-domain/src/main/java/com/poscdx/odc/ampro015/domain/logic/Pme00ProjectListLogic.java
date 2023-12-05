package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.*;
import com.poscdx.odc.ampro015.domain.lifecycle.ServiceLifecycle;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectListService;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectListStore;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class Pme00ProjectListLogic implements Pme00ProjectListService {
    private final Pme00ProjectListStore store;

    public Pme00ProjectListLogic(Pme00ProjectListStore store) {
        this.store = store;
    }

    @Override
    public Pme00ProjectListDto find(String cdVId) {
        return this.store.retrieve(cdVId);
    }

    @Override
    public List<Pme00ProjectListDto> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public void modify(List<Pme00ProjectListDto> entityList) {
        entityList.forEach(this.store::update);
    }

    @Override
    public Pme00ProjectListDto register(Pme00ProjectListDto entity) {
        return this.store.create(entity);
    }

    @Override
    public void remove(String cdVId) {
        this.store.delete(cdVId);
    }

    @Override
    public List<Pme00Member> getMember(String cdVId) {
        List<Object[]> resultList = this.store.getMember();
        List<Pme00Member> memberDtoList = new ArrayList<>();
        for (Object[] obj : resultList) {
            memberDtoList.add(new Pme00Member(obj));
        }
        return memberDtoList;
    }

}
