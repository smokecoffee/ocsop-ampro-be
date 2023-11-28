package com.poscdx.odc.ampro015.domain.logic;

import com.poscdx.odc.ampro015.domain.entity.Pme00ProjectInfo;
import com.poscdx.odc.ampro015.domain.spec.Pme00ProjectInfoService;
import com.poscdx.odc.ampro015.domain.store.Pme00ProjectInfoStore;

import java.util.List;

public class Pme00ProjectInfoLogic implements Pme00ProjectInfoService {
    private final Pme00ProjectInfoStore store;

    public Pme00ProjectInfoLogic(Pme00ProjectInfoStore store) {
        this.store = store;
    }

    @Override
    public Pme00ProjectInfo find(String cdVId) {
        return this.store.retrieve(cdVId);
    }

    @Override
    public List<Pme00ProjectInfo> findAll() {
        return this.store.retrieveAll();
    }

    @Override
    public Pme00ProjectInfo modify(Pme00ProjectInfo pme00ProjectInfo) {
        return this.store.update(pme00ProjectInfo);
    }

    @Override
    public void remove(String cdVId) {
        this.store.delete(cdVId);
    }
}
